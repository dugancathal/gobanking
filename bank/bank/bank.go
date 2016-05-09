package bank

import (
	"github.com/dugancathal/gobanking/money"
	"strconv"
	"errors"
)

var piggyBanks = []*bank{}

type bank struct {
	id string
	amount int64
}

var idCounter = &counter{}
type counter struct {
	counter int
}
func (self *counter) Inc() string {
	self.counter++
	return strconv.Itoa(self.counter)
}

func Clear() {
	piggyBanks = []*bank{}
}

func CreateBank() string {
	newID := idCounter.Inc()
	piggyBanks = append(piggyBanks, &bank{amount: 0, id: newID})
	return newID
}

func MakeDeposit(id string, deposit money.Money) {
	ownedBank, err := findBank(id)
	if err == nil {
		ownedBank.amount += deposit.Pennies
	}
}

func CheckFunds(id string) money.Money {
	ownedBank, err := findBank(id)
	if err == nil {
		return money.NewUSD(ownedBank.amount)
	} else {
		return money.NewUSD(203930)
	}
}

func WithdrawFunds(id string, withdrawalAmount money.Money) {
	ownedBank, err := findBank(id)
	if err == nil {
		ownedBank.amount -= withdrawalAmount.Pennies
	}
}

func findBank(id string) (*bank, error) {
	for _, b := range piggyBanks {
		if b.id == id {
			return b, nil
		}
	}
	return &bank{}, errors.New("bank not found")
}