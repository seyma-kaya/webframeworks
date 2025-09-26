import fetchIntercept from 'fetch-intercept';
export class FetchInterceptor {
    static theInstance;
    session;
    router;
    unregister;

    constructor(session, router) {
        this.session = session;
        this.router = router;

        FetchInterceptor.theInstance = this;
        this.unregister = fetchIntercept.register(this);
        console.log("FetchInterceptor has been registered; current token=",
            FetchInterceptor.theInstance.session._currentToken);
    }

    request(url, options) {
        let token = FetchInterceptor.theInstance.session._currentToken;

        if (token == null) {
            return [url, options];
        }else if (options == null){
            return [url, {headers: {Authorization: token}}]
        }else{
            let newOptions = {...options };
            newOptions.headers = {Authorization: token}
        }
    }

    async handleErrorInResponse(response) {
        if (response.status === 401) {
            this.router.push({ path: '/sign-out',});   // vue-router
        }
    }
    response(response) {
        // console.log("FetchInterceptor response: ", response);
        if (response.status >= 400 && response.status < 600) {
            FetchInterceptor.theInstance.handleErrorInResponse(response);
        }
        return response;
    }


}