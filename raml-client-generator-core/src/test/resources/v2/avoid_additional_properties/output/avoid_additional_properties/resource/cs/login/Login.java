
package avoid_additional_properties.resource.cs.login;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import avoid_additional_properties.exceptions.FooException;
import avoid_additional_properties.responses.FooResponse;

public class Login {

    private String _baseUrl;
    private Client _client;

    public Login() {
        _baseUrl = null;
        _client = null;
    }

    public Login(String baseUrl, Client _client) {
        _baseUrl = (baseUrl +"/login");
        this._client = _client;
    }

    protected Client getClient() {
        return this._client;
    }

    private String getBaseUri() {
        return _baseUrl;
    }

    public FooResponse<String> post() {
        WebTarget target = this._client.target(getBaseUri());
        final javax.ws.rs.client.Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.method("POST", ((Entity) null));
        if (response.getStatusInfo().getFamily()!= Family.SUCCESSFUL) {
            Response.StatusType statusInfo = response.getStatusInfo();
            throw new FooException(statusInfo.getStatusCode(), statusInfo.getReasonPhrase(), response.getStringHeaders(), response);
        }
        FooResponse<String> apiResponse = new FooResponse<String>(((String) response.readEntity(Object.class)), response.getStringHeaders(), response);
        return apiResponse;
    }

}
