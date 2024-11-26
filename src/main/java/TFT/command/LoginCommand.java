package TFT.command;

import lombok.Data;

@Data
public class LoginCommand {
	String id;
	String password;
	String grade;
	boolean isAutoLogin;
	boolean isIdStore;
}
