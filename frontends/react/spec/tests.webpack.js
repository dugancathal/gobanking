var testContext = require.context('.', true, /Spec\.js/);
import 'whatwg-fetch';
testContext.keys().forEach(testContext);