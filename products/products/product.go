package products

import (
	"github.com/dugancathal/gobanking/money"
)

type Product struct {
	ID          string `json:"id"`
	Name        string `json:"name"`
	Description string `json:"description"`
	Price       money.Money `json:"price"`
}

var allProducts = []Product{
	Product{
		ID:  "8B750E99-3682-48FA-9D48-A0DA9109F740",
		Name: "TeddyBear",
		Description: "Soft and cuddly",
		Price: money.NewUSD(999),
	},
}