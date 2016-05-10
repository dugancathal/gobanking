package money

import (
	"fmt"
	"encoding/json"
)

type Money struct {
	Pennies  int64
	Currency Currency
}

type Currency string

const USD = Currency("USD")

func NewUSD(amount int64) Money {
	return Money{Pennies: amount, Currency: USD}
}

func Add(money1, money2 Money) Money {
	return Money{
		Pennies: money1.Pennies + money2.Pennies,
		Currency: money1.Currency,
	}
}

func Subtract(money1, money2 Money) Money {
	return Money{
		Pennies: money1.Pennies - money2.Pennies,
		Currency: money1.Currency,
	}
}

func (m Money) MarshalJSON() ([]byte, error) {
	return []byte(fmt.Sprintf(`{"money": %.02f, "currency": "%s"}`, float64(m.Pennies) / 100, m.Currency)), nil
}

func (m *Money) UnmarshalJSON(body []byte) error {
	money := Money{}
	var stuff map[string]interface{}
	err := json.Unmarshal(body, &stuff)
	if err != nil {
		fmt.Printf("ERROR: %s", err.Error())
	}
	money.Currency = Currency(stuff["currency"].(string))
	dollarsAndCents := stuff["money"].(float64)
	money.Pennies = int64(dollarsAndCents * 100)
	*m = money
	return nil
}
