//package com.example.demo.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.demo.utility.JwtUtil;
//
//
//@RestController
//public class KeycloakApis {
//	
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	//@PostMapping("/keycloakMasterToken")
//	public String getMasterToken() {
//		String url = "";
//		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//			url = "http://localhost:8080/realms/master/protocol/openid-connect/token";
//			requestBody.add("grant_type", "password");
//			requestBody.add("username", "admin");
//			requestBody.add("password", "12345678");
//			requestBody.add("client_id", "admin-cli");
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);
//		HashMap<Object,Object> response = (HashMap<Object, Object>) restTemplate.postForEntity(url, request, Object.class).getBody();
//		System.out.println(response);
//		return (String) response.get("access_token");
//	}
//
//	@GetMapping("/getAllResources")
//	public ResponseEntity<Object> getAllResource(@RequestParam String realm,@RequestParam String clientid)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/resource";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Object> request = new HttpEntity<>(headers);
//
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
//		//System.out.println(response);
//		return response;	
//	}
//	
//	@PostMapping("/addresource")
//	public ResponseEntity<Object> addResource(@RequestBody HashMap<Object,Object>data,@RequestParam String realm,@RequestParam String clientid)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/resource";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HashMap<Object,Object>newResource=new HashMap<Object,Object>();
//		newResource.put("name", data.get("name"));
//		newResource.put("display", data.get("display"));
//		newResource.put("ownerManagedAccess", false);
//		HashMap<Object,Object>scopesHash=new HashMap<>();
//		List<HashMap<Object,Object>>scopes=(List<HashMap<Object, Object>>) data.get("scopes");
//		List<HashMap<Object,Object>>scopesList=new ArrayList<>();
//		for(int i=0;i<scopes.size();i++)
//		{
//			scopesHash.put("name",scopes.get(i).get("name"));
//			scopesList.add(scopesHash);
//		}
//		newResource.put("scopes", scopesList);
//		List<String>uris=(List<String>) data.get("uris");
//		List<String>new_uris=new ArrayList<>();
//		for(int i=0;i<uris.size();i++)
//		{
//			new_uris.add(uris.get(i));
//		}
//		newResource.put("uris", new_uris);
//		HttpEntity<Object> request = new HttpEntity<>(newResource,headers);
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
//		return response;	
//	}
//	
//	@GetMapping("/getAllResourcePermission")
//	public ResponseEntity<Object> getAllResourcePermission(@RequestParam String realm,@RequestParam String clientid)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/permission";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Object> request = new HttpEntity<>(headers);
//
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
//		return response;	
//	}
//	
//	@PostMapping("/addresourcePermission")
//	public ResponseEntity<Object> addResourcePermission(@RequestParam String realm,@RequestParam String clientid,@RequestBody HashMap<String,Object> data)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/permission/scope";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HashMap<Object,Object> mainresource=new HashMap<>();
////		mainresource.put("name",data.get("name"));
////		mainresource.put("type", "scope");
////		List<Object>resourceList=new ArrayList<>();
////		resourceList.add(data.get("resources"));
////		mainresource.put("resources", resourceList);
////		
////		List<Object>policysList=(List<Object>) data.get("policys");
////		for(int i=0;i<policysList.size();i++)
////			
//		
//		HttpEntity<Object> request = new HttpEntity<>(data,headers);
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
//		return response;	
//	}
//	@PostMapping("/addresourcePolicy")
//	public ResponseEntity<Object> addResourcePolicy(@RequestParam String realm,@RequestParam String clientid,@RequestParam String userId,@RequestParam String name)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/policy/user";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HashMap<Object,Object> mainresource=new HashMap<>();
//		List<String>userlist=new ArrayList<>();
//		userlist.add(userId);
//		mainresource.put("users", userlist);
//		mainresource.put("name",name);
//		mainresource.put("type","user");
//		HttpEntity<Object> request = new HttpEntity<>(mainresource,headers);
//		System.out.println("request here");
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
//		return response;	
//	}
//	@GetMapping("/report/billing/getAllResourcePolicys")
//	public ResponseEntity<Object> getAllResourcePolicys(@RequestParam String realm,@RequestParam String clientid)
//	{
//		System.out.println("************");
//		String url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/policy";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Object> request = new HttpEntity<>(headers);
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
//		return response;	
//	}
//	
//	@GetMapping(value = "/report/billing/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getReportBill(@PathVariable("id") String id,@RequestHeader("Authorization")String header) {
//		String resource_url="http://localhost:8080/admin/realms/Laboratory/clients/a3e77ff0-15e7-4864-91b8-3ae6ec68f415/authz/resource-server/policy/evaluate";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HashMap<Object,Object> mainresource=new HashMap<>();
//		String[] parts = header.split("\\s+");
//		DecodedJWT decodedJWT = JWT.decode(parts[1]);
//		 Map<String, Claim> jwt=decodedJWT.getClaims();
//		 //System.out.println(jwt);
//		 String userId=jwt.get("sub").asString();
//		mainresource.put("userId",userId);
//		List<HashMap<Object, Object>>resoucesList=new ArrayList<>();
//		HashMap<Object,Object>resouceHash=new HashMap<>();
//		//List<HashMap<Object,Object>>resources=(List<HashMap<Object, Object>>) data.get("resources");
//		resouceHash.put("name","/report/billing/"+id);
//		List<HashMap<Object,Object>>scopesList=new ArrayList<>();
//		HashMap<Object,Object>scopesHash=new HashMap<>();
//		//List<HashMap<Object,Object>>scopes=(List<HashMap<Object, Object>>) resources.get(0).get("scopes");
//		scopesHash.put("name","view");
//		scopesList.add(scopesHash);
//		resouceHash.put("scopes", scopesList);
//		resoucesList.add(resouceHash);
//		mainresource.put("resources",resoucesList);
//		
//		System.out.println(mainresource);		
//		HttpEntity<Object> request = new HttpEntity<>(mainresource,headers);
////		List<HashMap<Object,Object>>resourcesList=new ArrayList<>();
//		HashMap<Object,Object> response = (HashMap<Object, Object>) restTemplate.exchange(resource_url, HttpMethod.POST, request, Object.class).getBody();
//		//return response;
//		if(response.get("status").equals("DENY"))
//			return "Deny";
//		else
//			return "permit";
//		
//		
//       // return new ResponseEntity<>("GET Report Billing API triggered"+id, null, HttpStatus.SC_OK);
//    }
//	
//	@PostMapping("/report/billing/evaluate")
//	public HashMap<Object, Object> evaluateAllResources(@RequestBody HashMap<Object,Object>data,@RequestHeader(name="Authorization")String header)
//	{
//		String resource_url="http://localhost:8080/admin/realms/Laboratory/clients/a3e77ff0-15e7-4864-91b8-3ae6ec68f415/authz/resource-server/policy/evaluate";
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		// getting masterToken
//		String masterToken = getMasterToken();
//		headers.add("Authorization", "Bearer " + masterToken);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HashMap<Object,Object> mainresource=new HashMap<>();
//		String[] parts = header.split("\\s+");
//		DecodedJWT decodedJWT = JWT.decode(parts[1]);
//		 Map<String, Claim> jwt=decodedJWT.getClaims();
//		 //System.out.println(jwt);
//		 String userId=jwt.get("sub").asString();
//		mainresource.put("userId",userId);
//		List<HashMap<Object, Object>>resoucesList=new ArrayList<>();
//		HashMap<Object,Object>resouceHash=new HashMap<>();
//		List<HashMap<Object,Object>>resources=(List<HashMap<Object, Object>>) data.get("resources");
//		resouceHash.put("name",resources.get(0).get("name"));
//		List<HashMap<Object,Object>>scopesList=new ArrayList<>();
//		HashMap<Object,Object>scopesHash=new HashMap<>();
//		List<HashMap<Object,Object>>scopes=(List<HashMap<Object, Object>>) resources.get(0).get("scopes");
//		scopesHash.put("name",scopes.get(0).get("name"));
//		scopesList.add(scopesHash);
//		resouceHash.put("scopes", scopesList);
//		resoucesList.add(resouceHash);
//		mainresource.put("resources",resoucesList);
//		
//		System.out.println(mainresource);		
//		HttpEntity<Object> request = new HttpEntity<>(mainresource,headers);
////		List<HashMap<Object,Object>>resourcesList=new ArrayList<>();
//		HashMap<Object,Object> response = (HashMap<Object, Object>) restTemplate.exchange(resource_url, HttpMethod.POST, request, Object.class).getBody();
//		return response;
//	}
//	
//	
//	
//	@PostMapping("/report/billing/addresource/{phradd}")
//	public HashMap<Object, Object> addResource(@RequestParam (name="permission_name")String permission_name,@RequestParam (name="policy_name") String policyname,@RequestHeader("Authorization") String header,@RequestParam String userId,@PathVariable String phradd,@RequestBody HashMap<Object,Object>data,@RequestParam String realm,@RequestParam String clientid)
//	{
//	String resource_url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/resource";
//	RestTemplate restTemplate = new RestTemplate();
//	HttpHeaders headers = new HttpHeaders();
//	// getting masterToken
//	String masterToken = getMasterToken();
//	headers.add("Authorization", "Bearer " + masterToken);
//	headers.setContentType(MediaType.APPLICATION_JSON);
//	HashMap<Object,Object>newResource=new HashMap<Object,Object>();
//	newResource.put("name", data.get("name"));
//	newResource.put("ownerManagedAccess", false);
//	HashMap<Object,Object>scopesHash=new HashMap<>();
//	List<HashMap<Object,Object>>scopes=(List<HashMap<Object, Object>>) data.get("scopes");
//	List<HashMap<Object,Object>>scopesList=new ArrayList<>();
//	for(int i=0;i<scopes.size();i++)
//	{
//		scopesHash.put("name",scopes.get(i).get("name"));
//		scopesList.add(scopesHash);
//	}
//	newResource.put("scopes", scopesList);
//	String uris=phradd+"/addresource/*";
//	List<String>new_uris=new ArrayList<>();
//		new_uris.add(uris);
//	newResource.put("uris", new_uris);
//	HttpEntity<Object> request = new HttpEntity<>(newResource,headers);
//	HashMap<Object,Object> response = (HashMap<Object, Object>) restTemplate.exchange(resource_url, HttpMethod.POST, request, Object.class).getBody();
//	String resourceId=(String) response.get("_id");
//	List<HashMap<Object,Object>> scopes_hash=(List<HashMap<Object, Object>>) response.get("scopes");
//	List<Object>scopes_list=new ArrayList<>();
//	for(int i=0;i<scopes_hash.size();i++)
//	{
//		scopes_list.add(scopes_hash.get(i).get("id"));
//	}
//	String policy_url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/policy/user";
//	HashMap<Object,Object> mainresource=new HashMap<>();
//	List<String>userlist=new ArrayList<>();
//	userlist.add(userId);
//	mainresource.put("users", userlist);
//	mainresource.put("name",policyname);
//	mainresource.put("type","user");
//	HttpEntity<Object> request1 = new HttpEntity<>(mainresource,headers);
//	System.out.println("request here");
//	HashMap<Object,Object>  response1 = (HashMap<Object, Object>) restTemplate.exchange(policy_url, HttpMethod.POST, request1, Object.class).getBody();
//	//System.out.println(response1);
//	String policy_id= (String) response1.get("id");
//	System.out.println();
//	String permission_url="http://localhost:8080/admin/realms/"+realm+"/clients/"+clientid+"/authz/resource-server/permission/scope";
//	HashMap<Object,Object>permissionResource=new HashMap<>();
//	permissionResource.put("name",permission_name);
//	permissionResource.put("type", "scope");
//	List<Object>resourceList=new ArrayList<>();
//	resourceList.add(resourceId);
//	permissionResource.put("resources", resourceList);
//	List<Object>policysList=new ArrayList<>();
//	policysList.add(policy_id);
//	permissionResource.put("policies", policysList);
//	List<Object>scopes_List=new ArrayList<>();
//	scopes_List.add(policy_id);
//	permissionResource.put("scopes", scopes_list);
//	HttpEntity<Object> request2 = new HttpEntity<>(permissionResource,headers);
//	HashMap<Object,Object>  response2 = (HashMap<Object, Object>) restTemplate.exchange(permission_url, HttpMethod.POST, request2, Object.class).getBody();
//	return response2;
//	}
//	
//}
