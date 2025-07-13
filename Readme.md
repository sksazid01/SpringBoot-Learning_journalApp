## HTTP Status Code Classes (with "short words" / phrases)

### **1xx – Informational**
- **100 Continue** – Request received, continue process.
- **101 Switching Protocols** – Switching to the new protocol as requested.

### **2xx – Success**
- **200 OK** – Request successful.
- **201 Created** – Request successful, resource created.
- **202 Accepted** – Request accepted, processing not complete.
- **204 No Content** – Request successful, no content to return.

### **3xx – Redirection**
- **301 Moved Permanently** – Resource moved to a new URL.
- **302 Found** – Resource temporarily available at a different URL.
- **303 See Other** – See another URL for the response.
- **304 Not Modified** – Resource not changed, use cached version.

### **4xx – Client Error**
- **400 Bad Request** – Client sent invalid request.
- **401 Unauthorized** – Authentication needed.
- **403 Forbidden** – Client not allowed access.
- **404 Not Found** – Resource not found.
- **405 Method Not Allowed** – HTTP method not supported.
- **409 Conflict** – Request conflicts with resource state.

### **5xx – Server Error**
- **500 Internal Server Error** – Generic server failure.
- **501 Not Implemented** – Server cannot process request.
- **502 Bad Gateway** – Invalid response from upstream server.
- **503 Service Unavailable** – Server temporarily unavailable.
- **504 Gateway Timeout** – Server did not receive a timely response.

---

## Table for Quick Reference

| Code | Short Word              | Meaning                                     |
|------|------------------------|---------------------------------------------|
| 100  | Continue               | Keep going, initial part received           |
| 200  | OK                     | Successful response                         |
| 201  | Created                | New resource created                        |
| 204  | No Content             | No data to send back                        |
| 301  | Moved Permanently      | Resource URL changed                        |
| 302  | Found                  | Temporary redirect                          |
| 304  | Not Modified           | Use cached data                             |
| 400  | Bad Request            | Bad/malformed request                       |
| 401  | Unauthorized           | Needs authentication                        |
| 403  | Forbidden              | Not permitted                               |
| 404  | Not Found              | Resource missing                            |
| 409  | Conflict               | Request in conflict                         |
| 500  | Internal Server Error  | General server error                        |
| 502  | Bad Gateway            | Bad upstream response                       |
| 503  | Service Unavailable    | Server down or busy                         |
| 504  | Gateway Timeout        | Upstream server didn’t respond              |









![image](https://github.com/user-attachments/assets/a8dd7fff-3f42-4057-b4fc-35e8e08553c2)

# Authentication vs. Authorization in Web Context

## Authentication: WHO you are
- **Web Example**: Login page where users enter credentials
- **UI Elements**: Username/password fields, "Sign In" buttons, "Forgot Password" links
- **Response**: "Invalid username or password" errors
- **Technical**: Login cookies, JWT tokens, session IDs

## Authorization: WHAT you can do
- **Web Example**: Access control to specific pages or features
- **UI Elements**: Hidden menu items, disabled buttons, restricted sections
- **Response**: "403 Forbidden" errors, "Access Denied" pages
- **Technical**: Role checks, permission flags, route guards


## Enable Authentication
![image](https://github.com/user-attachments/assets/572a4555-fc97-4df2-bf1b-cfe325263e29)


Create Multiple application.yml files for the developer and production team. <br>
<img width="336" height="249" alt="image" src="https://github.com/user-attachments/assets/3273f06a-035a-429d-a300-a35158fca95f" />
```
spring:
  profiles:
    active: dev # it runs application-dev.yml
```


