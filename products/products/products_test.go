package products_test

import (
	"github.com/dugancathal/gobanking/products/products"

	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"
	"net/http/httptest"
	"net/http"
	"strings"
	"encoding/json"
)

var _ = Describe("Products", func() {
	Describe("GET /products", func() {
		It("can retrieve all products", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/products", strings.NewReader(""))
			products.IndexFunc(response, request)
			Expect(response.Code).To(Equal(http.StatusOK))

			var allProducts []products.Product
			json.Unmarshal(response.Body.Bytes(), &allProducts)
			Expect(len(allProducts)).To(Equal(1))
		})
	})

	Describe("GET /products/:id", func() {
		It("can retrieve a product by ID", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/products/8B750E99-3682-48FA-9D48-A0DA9109F740", strings.NewReader(""))
			products.GetFunc(response, request)
			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(decodeBody(response).Name).To(Equal("TeddyBear"))
		})

		It("can retrieve a product by Name", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/products/TeddyBear", strings.NewReader(""))
			products.GetFunc(response, request)
			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(decodeBody(response).ID).To(Equal("8B750E99-3682-48FA-9D48-A0DA9109F740"))
		})
	})
})

func decodeBody(response *httptest.ResponseRecorder) products.Product {
	var product products.Product
	json.Unmarshal(response.Body.Bytes(), &product)
	return product
}