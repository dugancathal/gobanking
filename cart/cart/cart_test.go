package cart_test

import (
	"github.com/dugancathal/gobanking/cart/cart"

	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"
	"net/http/httptest"
	"net/http"
	"strings"
)

var _ = Describe("Cart", func() {
	BeforeEach(func() {
		cart.Clear()
	})

	Describe("GET /cart/items", func() {
		It("returns nothing initially", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/cart/items", strings.NewReader(""))
			cart.GetCartHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentProductIDs()).To(BeEmpty())
		})

		It("returns the product ID when a product has been added", func() {
			cart.AddProductID("fancy-id")
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("GET", "/cart/items", strings.NewReader(""))
			cart.GetCartHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentProductIDs()).To(Equal([]string{"fancy-id"}))
		})
	})

	Describe("POST /cart/items", func() {
		It("adds the product ID to the cart", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", "/cart/items", strings.NewReader(`{"product_id": "another-product"}`))
			cart.AddProductHandler(response, request)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentProductIDs()).To(Equal([]string{"another-product"}))
		})

		It("does not uniq the product listing", func() {
			response := httptest.NewRecorder()
			request, _ := http.NewRequest("POST", "/cart/items", strings.NewReader(`{"product_id": "another-product"}`))
			cart.AddProductHandler(response, request)
			anotherRequest, _ := http.NewRequest("POST", "/cart/items", strings.NewReader(`{"product_id": "another-product"}`))
			cart.AddProductHandler(response, anotherRequest)

			Expect(response.Code).To(Equal(http.StatusOK))
			Expect(currentProductIDs()).To(Equal([]string{"another-product", "another-product"}))
		})
	})
})

func currentProductIDs() []string {
	return cart.ProductIDs()
}
