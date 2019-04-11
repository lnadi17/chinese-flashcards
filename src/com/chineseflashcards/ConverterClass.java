package com.chineseflashcards;

public class ConverterClass {
	public static String stringToPinyin(String str) {
		String res = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				res += toPinyinChar(str.charAt(i - 1), c);
				i--;
			} else {
				// If it has no tone, 'v' still needs to be converted to 'ü'
				if (c == 'v') {
					res += "ü";
				} else {
					res += c;
				}
			}
		}
		return reverse(res);
	}

	// Hack
	private static String toPinyinChar(char alpha, char digit) {
		int tone = digit - '0';
		String res = "";

		switch (Character.toLowerCase(alpha)) {
		case 'a':
			res += "āáǎà".charAt(tone - 1);
			break;
		case 'e':
			res += "ēéěè".charAt(tone - 1);
			break;
		case 'i':
			res += "īíǐì".charAt(tone - 1);
			break;
		case 'o':
			res += "ōóǒò".charAt(tone - 1);
			break;
		case 'u':
			res += "ūúǔù".charAt(tone - 1);
			break;
		case 'v':
			res += "ǖǘǚǜ".charAt(tone - 1);
			break;
		default:
			res += "!rorre!";
			break;
		}

		return res;
	}

	private static String reverse(String str) {
		String res = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			res += str.charAt(i);
		}
		return res;
	}
}
