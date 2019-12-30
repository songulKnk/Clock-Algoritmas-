import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClockAlgo {

	static HashMap<String, Integer> hashMap = new HashMap<>();
	static ArrayList<String> buffer = new ArrayList<>();
	static int isaretci = 0;

	public static void guncellemeKontrol(String kelime) {
		while (isaretci <= 4) {
			if (hashMap.get(buffer.get(isaretci)).equals(1)) {
				hashMap.replace(buffer.get(isaretci), 0);
			} else {
				buffer.set(isaretci, kelime);
				hashMap.replace(kelime, 1);
				break;
			}
			isaretci++;
		}
		if (hashMap.get(kelime)==1) {
			isaretci++;
		} else {
			isaretci = 0;
		}
	}

	public static void yeniEklemeKontrol(String kelime) {
		while (isaretci <= 4) {
			if (hashMap.get(buffer.get(isaretci)).equals(1)) {
				hashMap.replace(buffer.get(isaretci), 0);
			} else {
				buffer.set(isaretci, kelime);
				hashMap.put(kelime, 1);
				break;
			}
			isaretci++;
			bufferGoruntule();
			if(isaretci!=5)
				System.out.println("\nİşaretçi  : " + isaretci);

		}
		if (hashMap.containsKey(kelime)) {
			isaretci++;
		} else {
			isaretci = 0;
			System.out.println("\n1 tur \nİşaretçi : " + isaretci);
		}
	}

	public static void bufferGoruntule() {
		for (String kelime : buffer) {
			System.out.print(kelime + ":" + hashMap.get(kelime) + " , ");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print("\nCümle Girin: ");
			String[] kelimeler = input.nextLine().split(" ");

			for (String kelime : kelimeler) {

				if (isaretci > 5) {
					isaretci = 0;
					System.out.println("\n1 tur");
				}
				System.out.println("\nİşaretçi : " + isaretci);

				if (!buffer.contains(kelime) && buffer.size() < 5) {
					buffer.add(isaretci++, kelime);
					hashMap.put(kelime, 1);
				} 
				else if (!buffer.contains(kelime) && buffer.size() == 5 && !hashMap.containsKey(kelime)) {
					if (isaretci == 5) {
						isaretci = 0;
						System.out.println("\n1 tur");
					}
					while (!hashMap.containsKey(kelime)) {
						yeniEklemeKontrol(kelime);
					}
				} 
				else if(!buffer.contains(kelime) && buffer.size() == 5 && hashMap.containsKey(kelime)) {
					if (isaretci == 5) {
						isaretci = 0;
						System.out.println("\n1 tur");
					}
					while (hashMap.get(kelime)!=1) {
						guncellemeKontrol(kelime);
					}
				}

				else if (buffer.contains(kelime) && buffer.size() == 5) {
					if (hashMap.get(kelime) == 0) {
						hashMap.replace(kelime, 1);
						isaretci++;
					}
				}

				bufferGoruntule();
			}
		}
	}
}