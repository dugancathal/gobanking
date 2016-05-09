package bank_test

import (
	. "github.com/onsi/ginkgo"
	. "github.com/onsi/gomega"

	"testing"
)

func TestBank(t *testing.T) {
	RegisterFailHandler(Fail)
	RunSpecs(t, "Bank Suite")
}
