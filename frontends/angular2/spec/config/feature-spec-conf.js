exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['../features/*.spec.js'],
    baseUrl: 'http://localhost:8080',
    useAllAngular2AppRoots: true
};