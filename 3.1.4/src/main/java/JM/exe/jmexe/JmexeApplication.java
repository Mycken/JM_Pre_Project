package main.java.JM.exe.jmexe;

import JM.exe.jmexe.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JmexeApplication {

	public static void main(String[] args) {

		SpringApplication.run(JmexeApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();

//	Запрос на получение всех юзеров
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.GET, entity, String.class);
		String cookie = response.getHeaders().get("Set-Cookie").toString().substring(1,62);
		System.out.println();
		System.out.print("Заголовок Set-Cookie: ");// Определяем значение заголовка Set-Cookie
		System.out.print("as string: "+cookie);

//	Добаление нового юзера
		User user = new User(3L,"James","Brown",(byte)50);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Cookie",cookie);
		HttpEntity<User> entity_add = new HttpEntity<>(user,headers);
		System.out.println("Запрос на добавление = user+header: " +  entity_add);

		ResponseEntity<String> response_add = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.POST, entity_add, String.class);
		//Получаем первую часть кода  5ebfeb
		System.out.println("Первая часть кода: "+response_add.getBody());

//  Запрос на изменение юзера
		user.setName("Thomas");
		user.setLastName("Shelby");
		HttpEntity<User> entity_up = new HttpEntity<>(user,headers);
		System.out.println("Запрос на изменение = user+header: " +  entity_up);

		ResponseEntity<String> response_up = restTemplate.exchange("http://91.241.64.178:7081/api/users/", HttpMethod.PUT, entity_up, String.class);
		// Получаем вторую часть кода e7cb97
		System.out.println("Вторая часть кода: " + response_up.getBody());

//  Запрос на удаление юзера

		System.out.println("Запрос на удаление = header: " +  entity_up);
		ResponseEntity<String> response_del = restTemplate.exchange("http://91.241.64.178:7081/api/users/3", HttpMethod.DELETE, entity_up, String.class);
// Получаем третью часть кода. 5dfcf9
		System.out.println("Третья часть кода: " + response_del.getBody());
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
