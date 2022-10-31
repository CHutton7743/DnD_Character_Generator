package com.Crucible.Forge;

import com.Crucible.Forge.Character_Resources.Class;
import com.Crucible.Forge.Character_Resources.Gender;
import com.Crucible.Forge.Character_Resources.Race;
import com.Crucible.Forge.Character_Resources.SubRace;
import com.Crucible.Forge.Entities_and_Repositories.Character;
import com.Crucible.Forge.Entities_and_Repositories.CharacterRepository;
import com.Crucible.Forge.Entities_and_Repositories.User;
import com.Crucible.Forge.Entities_and_Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForgeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ForgeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User(1, "Codey", "Hutton", "Codeyhutton12@gmail.com"));
		this.userRepository.save(new User(2, "Natalie", "Hutton", "misspage1993@gmail.com"));
		this.characterRepository.save(new Character("Codey", "Male", "Human", 1L, "Fighter", 1));
	}
	@Autowired
	UserRepository userRepository;
	@Autowired
	CharacterRepository characterRepository;
}
