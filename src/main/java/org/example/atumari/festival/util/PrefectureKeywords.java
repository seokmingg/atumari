package org.example.atumari.festival.util;

import java.util.HashMap;
import java.util.Map;

public class PrefectureKeywords {
	
	public static Map<String, String[]> getPrefectureKeywords(){
		Map<String, String[]> prefectureKeywords = new HashMap<>();

// 北海道 홋카이도(1)
		prefectureKeywords.put(
		    "北海道",
		    new String[]{"北海道", "Hokkaido", "札幌", "Sapporo", "函館", "Hakodate", "旭川", "Asahikawa", "小樽", "Otaru"}
		);

// 東北 도호쿠(6)

		// 青森県 아오모리현
		prefectureKeywords.put(
		    "青森県",
		    new String[]{"青森県", "青森", "Aomori", "弘前", "Hirosaki", "八戸", "Hachinohe"}
		);

		// 岩手県 이와테현
		prefectureKeywords.put(
		    "岩手県",
		    new String[]{"岩手県", "岩手", "Iwate", "盛岡", "Morioka", "花巻", "Hanamaki", "平泉", "Hiraizumi"}
		);

		// 宮城県 미야기현
		prefectureKeywords.put(
		    "宮城県",
		    new String[]{"宮城県", "宮城", "Miyagi", "仙台", "Sendai", "石巻", "Ishinomaki", "松島", "Matsushima"}
		);

		// 秋田県 아키타현
		prefectureKeywords.put(
		    "秋田県",
		    new String[]{"秋田県", "秋田", "Akita", "横手", "Yokote", "角館", "Kakunodate"}
		);

		// 山形県 야마가타현
		prefectureKeywords.put(
		    "山形県",
		    new String[]{"山形県", "山形", "Yamagata", "米沢", "Yonezawa", "鶴岡", "Tsuruoka", "酒田", "Sakata"}
		);

		// 福島県 후쿠시마현
		prefectureKeywords.put(
		    "福島県",
		    new String[]{"福島県", "福島", "Fukushima", "郡山", "Koriyama", "会津若松", "Aizuwakamatsu"}
		);


// 関東 간토(7)

		// 茨城県 이바라키현
		prefectureKeywords.put(
		    "茨城県",
		    new String[]{"茨城県", "茨城", "Ibaraki", "水戸", "Mito", "つくば", "Tsukuba", "日立", "Hitachi"}
		);

		// 栃木県 도치기현
		prefectureKeywords.put(
		    "栃木県",
		    new String[]{"栃木県", "栃木", "Tochigi", "宇都宮", "Utsunomiya", "日光", "Nikko", "足利", "Ashikaga"}
		);

		// 群馬県 군마현
		prefectureKeywords.put(
		    "群馬県",
		    new String[]{"群馬県", "群馬", "Gunma", "前橋", "Maebashi", "高崎", "Takasaki", "草津", "Kusatsu"}
		);

		// 埼玉県 사이타마현
		prefectureKeywords.put(
		    "埼玉県",
		    new String[]{"埼玉県", "埼玉", "Saitama", "さいたま市", "川越", "Kawagoe", "秩父", "Chichibu"}
		);

		// 千葉県 지바현
		prefectureKeywords.put(
		    "千葉県",
		    new String[]{"千葉県", "千葉", "Chiba", "成田", "Narita", "船橋", "Funabashi", "木更津", "Kisarazu"}
		);

		// 東京都 도쿄도
		prefectureKeywords.put(
		    "東京都",
		    new String[]{"東京都", "東京", "Tokyo", "新宿", "Shinjuku", "渋谷", "Shibuya", "浅草", "Asakusa", "八王子", "Hachioji"}
		);

		// 神奈川県 가나가와현
		prefectureKeywords.put(
		    "神奈川県",
		    new String[]{"神奈川県", "神奈川", "Kanagawa", "横浜", "Yokohama", "川崎", "Kawasaki", "鎌倉", "Kamakura", "箱根", "Hakone"}
		);


// 中部 주부(9)

		// 新潟県 니가타현
		prefectureKeywords.put(
		    "新潟県",
		    new String[]{"新潟県", "新潟", "Niigata", "長岡", "Nagaoka", "上越", "Joetsu","佐渡"}
		);

		// 富山県 도야마현
		prefectureKeywords.put(
		    "富山県",
		    new String[]{"富山県", "富山", "Toyama", "高岡", "Takaoka", "黒部", "Kurobe"}
		);

		// 石川県 이시카와현
		prefectureKeywords.put(
		    "石川県",
		    new String[]{"石川県", "石川", "Ishikawa", "金沢", "Kanazawa", "加賀", "Kaga", "輪島", "Wajima"}
		);

		// 福井県 후쿠이현
		prefectureKeywords.put(
		    "福井県",
		    new String[]{"福井県", "福井", "Fukui", "敦賀", "Tsuruga", "越前", "Echizen"}
		);

		// 山梨県 야마나시현
		prefectureKeywords.put(
		    "山梨県",
		    new String[]{"山梨県", "山梨", "Yamanashi", "甲府", "Kofu", "富士吉田", "Fujiyoshida", "河口湖", "Kawaguchiko"}
		);

		// 長野県 나가노현
		prefectureKeywords.put(
		    "長野県",
		    new String[]{"長野県", "長野", "Nagano", "松本", "Matsumoto", "軽井沢", "Karuizawa", "諏訪", "Suwa"}
		);

		// 岐阜県 기후현
		prefectureKeywords.put(
		    "岐阜県",
		    new String[]{"岐阜県", "岐阜", "Gifu", "高山", "Takayama", "飛騨", "Hida", "白川郷", "Shirakawago"}
		);

		// 静岡県 시즈오카현
		prefectureKeywords.put(
		    "静岡県",
		    new String[]{"静岡県", "静岡", "Shizuoka", "浜松", "Hamamatsu", "熱海", "Atami", "伊豆", "Izu"}
		);

		// 愛知県 아이치현
		prefectureKeywords.put(
		    "愛知県",
		    new String[]{"愛知県", "愛知", "Aichi", "名古屋", "Nagoya", "豊田", "Toyota", "岡崎", "Okazaki"}
		);


// 近畿 긴키(7)

		// 三重県 미에현
		prefectureKeywords.put(
		    "三重県",
		    new String[]{"三重県", "三重", "Mie", "津市", "Tsu", "伊勢", "Ise", "四日市", "Yokkaichi", "鳥羽", "Toba"}
		);

		// 滋賀県 시가현
		prefectureKeywords.put(
		    "滋賀県",
		    new String[]{"滋賀県", "滋賀", "Shiga", "大津", "Otsu", "彦根", "Hikone", "長浜", "Nagahama"}
		);

		// 京都府 교토부
		prefectureKeywords.put(
		    "京都府",
		    new String[]{"京都府", "京都", "Kyoto", "宇治", "Uji", "舞鶴", "Maizuru", "福知山", "Fukuchiyama"}
		);

		// 大阪府 오사카부
		prefectureKeywords.put(
		    "大阪府",
		    new String[]{"大阪府", "大阪", "Osaka", "堺", "Sakai", "吹田", "Suita", "岸和田", "Kishiwada"}
		);

		// 兵庫県 효고현
		prefectureKeywords.put(
		    "兵庫県",
		    new String[]{"兵庫県", "兵庫", "Hyogo", "神戸", "Kobe", "姫路", "Himeji", "西宮", "Nishinomiya", "明石", "Akashi"}
		);

		// 奈良県 나라현
		prefectureKeywords.put(
		    "奈良県",
		    new String[]{"奈良県", "奈良", "Nara", "橿原", "Kashihara", "吉野", "Yoshino"}
		);

		// 和歌山県 와카야마현
		prefectureKeywords.put(
		    "和歌山県",
		    new String[]{"和歌山県", "和歌山", "Wakayama", "田辺", "Tanabe", "高野山", "Koyasan", "白浜", "Shirahama"}
		);


// 中国 주고쿠(5)

		// 鳥取県 돗토리현
		prefectureKeywords.put(
		    "鳥取県",
		    new String[]{"鳥取県", "鳥取", "Tottori", "米子", "Yonago", "倉吉", "Kurayoshi"}
		);

		// 島根県 시마네현
		prefectureKeywords.put(
		    "島根県",
		    new String[]{"島根県", "島根", "Shimane", "松江", "Matsue", "出雲", "Izumo", "浜田", "Hamada"}
		);

		// 岡山県 오카야마현
		prefectureKeywords.put(
		    "岡山県",
		    new String[]{"岡山県", "岡山", "Okayama", "倉敷", "Kurashiki", "津山", "Tsuyama"}
		);

		// 広島県 히로시마현
		prefectureKeywords.put(
		    "広島県",
		    new String[]{"広島県", "広島", "Hiroshima", "福山", "Fukuyama", "尾道", "Onomichi", "宮島", "Miyajima"}
		);

		// 山口県 야마구치현
		prefectureKeywords.put(
		    "山口県",
		    new String[]{"山口県", "山口", "Yamaguchi", "下関", "Shimonoseki", "萩", "Hagi", "岩国", "Iwakuni"}
		);


// 四国 시코쿠(4)

		// 徳島県 도쿠시마현
		prefectureKeywords.put(
		    "徳島県",
		    new String[]{"徳島県", "徳島", "Tokushima", "鳴門", "Naruto", "阿波", "Awa"}
		);

		// 香川県 가가와현
		prefectureKeywords.put(
		    "香川県",
		    new String[]{"香川県", "香川", "Kagawa", "高松", "Takamatsu", "丸亀", "Marugame", "小豆島", "Shodoshima", "琴平"}
		);

		// 愛媛県 에히메현
		prefectureKeywords.put(
		    "愛媛県",
		    new String[]{"愛媛県", "愛媛", "Ehime", "松山", "Matsuyama", "今治", "Imabari", "宇和島", "Uwajima"}
		);

		// 高知県 고치현
		prefectureKeywords.put(
		    "高知県",
		    new String[]{"高知県", "高知", "Kochi", "四万十", "Shimanto", "室戸", "Muroto"}
		);


// 九州・沖縄 규슈・오키나와(8)

		// 福岡県 후쿠오카현
		prefectureKeywords.put(
		    "福岡県",
		    new String[]{"福岡県", "福岡", "Fukuoka", "北九州", "Kitakyushu", "久留米", "Kurume", "太宰府", "Dazaifu"}
		);

		// 佐賀県 사가현
		prefectureKeywords.put(
		    "佐賀県",
		    new String[]{"佐賀県", "佐賀", "Saga", "唐津", "Karatsu", "嬉野", "Ureshino"}
		);

		// 長崎県 나가사키현
		prefectureKeywords.put(
		    "長崎県",
		    new String[]{"長崎県", "長崎", "Nagasaki", "佐世保", "Sasebo", "島原", "Shimabara"}
		);

		// 熊本県 구마모토현
		prefectureKeywords.put(
		    "熊本県",
		    new String[]{"熊本県", "熊本", "Kumamoto", "阿蘇", "Aso", "天草", "Amakusa"}
		);

		// 大分県 오이타현
		prefectureKeywords.put(
		    "大分県",
		    new String[]{"大分県", "大分", "Oita", "別府", "Beppu", "由布院", "Yufuin", "日田", "Hita"}
		);

		// 宮崎県 미야자키현
		prefectureKeywords.put(
		    "宮崎県",
		    new String[]{"宮崎県", "宮崎", "Miyazaki", "高千穂", "Takachiho", "日南", "Nichinan", "延岡", "Nobeoka"}
		);

		// 鹿児島県 가고시마현
		prefectureKeywords.put(
		    "鹿児島県",
		    new String[]{"鹿児島県", "鹿児島", "Kagoshima", "霧島", "Kirishima", "指宿", "Ibusuki", "屋久島", "Yakushima"}
		);

		// 沖縄県 오키나와현
		prefectureKeywords.put(
		    "沖縄県",
		    new String[]{"沖縄県", "沖縄", "Okinawa", "那覇", "Naha", "石垣", "Ishigaki", "宮古島", "Miyakojima"}
		);
		
		return prefectureKeywords;
	}

}
