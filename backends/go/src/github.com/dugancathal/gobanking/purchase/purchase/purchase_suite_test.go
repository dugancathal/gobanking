package purchase_test

import (
	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"

	"testing"
)

func TestPurchase(t *testing.T) {
	RegisterFailHandler(Fail)
	RunSpecs(t, "Purchase Suite")
}
