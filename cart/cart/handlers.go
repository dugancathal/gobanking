package cart

import (
	"net/http"
	"encoding/json"
	"io/ioutil"
)

type addToCartBody struct {
	ProductId string `json:"product_id"`
}

func AddProductHandler(w http.ResponseWriter, r *http.Request) {
	body, _ := ioutil.ReadAll(r.Body)
	defer r.Body.Close()

	parsedBody := new(addToCartBody)
	json.Unmarshal(body, parsedBody)
	AddProductID(parsedBody.ProductId)
}

func GetCartHandler(w http.ResponseWriter, r *http.Request) {
	response, _ := json.Marshal(ProductIDs())
	w.Write(response)
}

func CartHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method == "POST" {
		AddProductHandler(w, r)
	} else {
		GetCartHandler(w, r)
	}
}