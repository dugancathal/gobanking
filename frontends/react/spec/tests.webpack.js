var testContext = require.context('.', true, /Spec\.js/);
testContext.keys().forEach(testContext);