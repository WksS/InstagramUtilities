package net.wkss.dev;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.utils.IGChallengeUtils;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws IGLoginException, ExecutionException, InterruptedException {
        Scanner code = new Scanner(System.in);

        Callable<String> inputCode = () -> {
            System.out.print("Please input code: ");
            return code.nextLine();
        };

        IGClient.Builder.LoginHandler challengeHandler = (client, response) -> IGChallengeUtils.resolveChallenge(client, response, inputCode);

        IGClient client = IGClient.builder()
                .username("yourUsername")
                .password("yourPassword")
                .onChallenge(challengeHandler)
                .login();

        String line = "\n";
        final String username, name, picture_url, biography, external_url;
        final int account_type, followers, following, media_count;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter here a username of person: ");

        username = client.getActions().users().findByUsername(scanner.next()).get().getUser().getUsername();
        name = client.getActions().users().findByUsername(scanner.next()).get().getUser().getFull_name();
        picture_url = client.getActions().users().findByUsername(scanner.next()).get().getUser().getProfile_pic_url();
        biography = client.getActions().users().findByUsername(scanner.next()).get().getUser().getBiography();
        external_url = client.getActions().users().findByUsername(scanner.next()).get().getUser().getExternal_url();
        account_type = client.getActions().users().findByUsername(scanner.next()).get().getUser().getAccount_type();
        followers = client.getActions().users().findByUsername(scanner.next()).get().getUser().getFollower_count();
        following = client.getActions().users().findByUsername(scanner.next()).get().getUser().getFollowing_count();
        media_count = client.getActions().users().findByUsername(scanner.next()).get().getUser().getMedia_count();

        System.out.println("Username: " + username + line +
                "Name: " + name + line +
                "Picture URL: " + picture_url + line +
                "External URL:" + external_url + line +
                "Account Type: " + account_type + line +
                "Followers: " + followers + line +
                "Following: " + following + line +
                "Media count: " + media_count + line + line +
                "Biography: " + biography + line);
    }
}