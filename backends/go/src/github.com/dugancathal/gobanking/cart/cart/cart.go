package cart

var userCart = cart{ProductIDs: []string{}}

type cart struct {
	ProductIDs []string
}

func Clear() {
	userCart.ProductIDs = []string{}
}

func AddProductID(productID string) {
	userCart.ProductIDs = append(userCart.ProductIDs, productID)
}

func ProductIDs() []string {
	return userCart.ProductIDs
}
