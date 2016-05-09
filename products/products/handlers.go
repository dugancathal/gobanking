package products

import (
	"net/http"
	"encoding/json"
)

func IndexFunc(w http.ResponseWriter, r *http.Request) {
	productJson, _ := json.Marshal(allProducts)
	w.Write([]byte(productJson))
}

func GetFunc(w http.ResponseWriter, r *http.Request) {
	productId := r.URL.Path[len("/products/"):]

	var chosenProduct Product
	for _, product := range allProducts {
		if product.Name == productId || product.ID == productId {
			chosenProduct = product
		}
	}

	productJson, _ := json.Marshal(chosenProduct)
	w.Write(productJson)
}
