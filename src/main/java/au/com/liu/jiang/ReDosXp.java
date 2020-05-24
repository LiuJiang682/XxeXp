package au.com.liu.jiang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReDosXp {

	public static void main(String[] args) {
		dosXp();
		NcXp();
		CpXp();
	}

	private static void CpXp() {
		System.out.println("========CpXp=======");
		final String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacb";
		final String regEx = "/(a+)+b/";
		System.out.println(System.currentTimeMillis());
		System.out.println(input.matches(Pattern.quote(regEx)));
		System.out.println(System.currentTimeMillis());
		
	}

	private static void NcXp() {
		System.out.println("========NcXp=======");
		final String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacb";
		final String regEx = "/(a+)+b/";
		System.out.println(System.currentTimeMillis());
		System.out.println(input.matches(regEx));
		System.out.println(System.currentTimeMillis());
	}

	private static void dosXp() {
		System.out.println("========dosXp=======");
		final String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacb";
		final String regEx = "/(a+)+b/";
		final String betterRegEx = "(/a+b/)";
		
		final Pattern p = Pattern.compile(regEx);
		final Matcher m = p.matcher(input);
		System.out.println(System.currentTimeMillis());
		System.out.println(m.matches());
		System.out.println(System.currentTimeMillis());
		System.out.println(m.find());
		System.out.println(System.currentTimeMillis());
		
		final Pattern p1 = Pattern.compile(betterRegEx);
		final Matcher m1 = p1.matcher(input);
		System.out.println(System.currentTimeMillis());
		System.out.println(m1.matches());
		System.out.println(System.currentTimeMillis());
		System.out.println(m1.find());
		System.out.println(System.currentTimeMillis());
	}

}
