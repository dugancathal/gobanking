package products_test

import (
	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"

	"testing"
)

func TestProducts(t *testing.T) {
	RegisterFailHandler(Fail)
	RunSpecs(t, "Products Suite")
}
