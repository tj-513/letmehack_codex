# LetMeHack

Travis Build Status
[![Build Status](https://travis-ci.org/travis-ci/travis-web.svg?branch=master)](https://travis-ci.org/travis-ci/travis-web)

Request/Response Format
All requests and responses must be in JSON, with the Content-Type header set to application/json.

Error Handling

For API to be consistent, always return error responses in json. JSON schema of the errors returned should be like the following:
```
{
"status": 401,
"message": "Invalid username or password.",
"developerMessage": "Login attempt failed because the specified password is incorrect."
}
```
