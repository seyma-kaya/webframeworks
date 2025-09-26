export class SessionSbService {
    BROWSER_STORAGE_ITEM_NAME;
    RESOURCES_URL;

    constructor(resourcesUrl, browserStorageItemName) {
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.RESOURCES_URL = resourcesUrl;
        //this.getTokenFromBrowserStorage();
    }

    getTokenFromBrowserStorage() {
        if (this._currentToken != null) return this._currentToken
        this._currentToken = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        let jsonAccount = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME+"_ACC");

        if (this._currentToken == null) {
            // TODO try to find the token+account in local storage and replicate to this session if found

        }
        if (jsonAccount != null) {
            this._currentAccount = JSON.parse(jsonAccount);
        }
        //console.log("SessionService recovered token: ", this._currentToken);
        //console.log("Current Account:", this._currentAccount);
        return this._currentToken;
    }

    saveTokenIntoBrowserStorage(token, user) {
        this._currentToken = token;
        this._currentUser = user;
        // allow for different user sessions from the same computer
        // sessionStorage keeps different items per browser tab
        // localStorage keeps a single item per browser vendor
        // both isolate the items per server domain of the page (including port number?)
        if (token == null) {
            this._currentUser = null;
            window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME+"_USER");
            // TODO remove the token+account from local storage, if localStorage and session storage are equal
        } else {
            console.log("New token for " + user.usersName + ": " + token);
            window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
            window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME+"_USER", JSON.stringify(user));
            // TODO also save the new token+account in localStorage
        }

    }

    async asyncSignIn(email, password) {
        const body = JSON.stringify({email: email, password: password});
        let response = await fetch(this.RESOURCES_URL + "/login",
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: body,
                credentials: 'include',
            })
        if (response.ok) {
            let user = await response.json();
            this.saveTokenIntoBrowserStorage(
                response.headers.get('Authorization'),
                user);
            return user;
        } else {
            console.log(response)
            return null;
        }
    }

    signOut() {
        this.saveTokenIntoBrowserStorage(null, null);

    }
    get currentToken() {
        return this._currentToken;
    }

    get currentAccount() {
        return this._currentUser;
    }

    isAdmin() {
        return this._currentAccount?.role?.toLowerCase().includes("admin");
    }
    isAuthenticated() {
        return this._currentUser != null;
    }
}