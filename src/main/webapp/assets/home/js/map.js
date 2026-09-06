/**
 *
 */

const japanMap = document.getElementById("japanMap");

japanMap.addEventListener("load", function() {

    const svgDoc = japanMap.contentDocument;


    // =========================
    // 8개 지역
    // =========================

    const regions = [

        "hokkaido",
        "tohoku",
        "kanto",
        "chubu",
        "kinki",
        "chugoku",
        "shikoku",
        "kyushu-okinawa"

    ];


    // =========================
    // 지역별 이름
    // =========================

    const regionNames = {

        "hokkaido": "北海道",
        "tohoku": "東北",
        "kanto": "関東",
        "chubu": "中部",
        "kinki": "近畿",
        "chugoku": "中国",
        "shikoku": "四国",
        "kyushu-okinawa": "九州・沖縄"

    };


    // =========================
    // 지역별 정보
    // =========================

	const regionInfo = {

	    "hokkaido": {
	        title: "北海道の祭り",
	        description:
	            "北海道で開催される祭りやイベントを紹介します。",
	        image: "assets/home/images/region/hokkaido.jpg"
	    },

	    "tohoku": {
	        title: "東北の祭り",
	        description:
	            "東北地方には、長い歴史と伝統を持つ祭りが数多くあります。",
	        image: "assets/home/images/region/tohoku.jpg"
	    },

	    "kanto": {
	        title: "関東の祭り",
	        description:
	            "東京をはじめ、関東各地で開催される祭りを紹介します。",
	        image: "assets/home/images/region/kanto.jpg"
	    },

	    "chubu": {
	        title: "中部の祭り",
	        description:
	            "中部地方の特色ある祭りやイベントを紹介します。",
	        image: "assets/home/images/region/chubu.jpg"
	    },

	    "kinki": {
	        title: "近畿の祭り",
	        description:
	            "京都や大阪など、歴史ある地域の祭りを紹介します。",
	        image: "assets/home/images/region/kinki.jpg"
	    },

	    "chugoku": {
	        title: "中国地方の祭り",
	        description:
	            "中国地方各地に伝わる伝統的な祭りを紹介します。",
	        image: "assets/home/images/region/chugoku.jpg"
	    },

	    "shikoku": {
	        title: "四国の祭り",
	        description:
	            "四国地方で開催される特色ある祭りを紹介します。",
	        image: "assets/home/images/region/shikoku.jpg"
	    },

	    "kyushu-okinawa": {
	        title: "九州・沖縄の祭り",
	        description:
	            "九州・沖縄ならではの文化や伝統を感じられる祭りを紹介します。",
	        image: "assets/home/images/region/kyushu-okinawa.jpg"
	    }

	};


    // =========================
    // 색상
    // =========================

    const defaultColor = "#E8EDE5";

    const hoverColor = "#F3C6CF";


    // =========================
    // 정보 영역
    // =========================

    const infoArea =
        document.getElementById("regionInfo");


    // =========================
    // 지역 정보 표시
    // =========================

	function showRegionInfo(region) {

	    const info =
	        regionInfo[region];

	    if (!info) {
	        return;
	    }

	    infoArea.innerHTML = `

	        

	        <h3>
	            ${regionNames[region]}
	        </h3>

	        <p>
	            ${info.description}
	        </p>

	        <div class="region-image">

	            <img
	                src="${info.image}"
	                alt="${regionNames[region]}">

	        </div>

	    `;

	}


    // =========================
    // 지역 색상 변경 함수
    // =========================

    function changeRegionColor(region, color) {

        const prefectures =
            svgDoc.querySelectorAll(
                "." + region + ".prefecture"
            );


        prefectures.forEach(function(area) {

            area.querySelectorAll("path, polygon")

                .forEach(function(shape) {

                    shape.style.fill = color;

                });

        });

    }


    // =========================
    // 8개 지역 처리
    // =========================

    regions.forEach(function(region) {

        const prefectures =
            svgDoc.querySelectorAll(
                "." + region + ".prefecture"
            );


        console.log(
            region,
            "→",
            prefectures.length
        );


        prefectures.forEach(function(prefecture) {


            // =========================
            // 기본 색상 설정
            // =========================

            prefecture.querySelectorAll(
                "path, polygon"
            )

                .forEach(function(shape) {

                    shape.style.fill =
                        defaultColor;

                });


            // =========================
            // 마우스 올리기
            // =========================

            prefecture.addEventListener(
                "mouseenter",
                function() {

                    // 기존 기능
                    changeRegionColor(
                        region,
                        hoverColor
                    );


                    // 추가 기능
                    showRegionInfo(
                        region
                    );

                }
            );


            // =========================
            // 마우스 나가기
            // =========================

            prefecture.addEventListener(
                "mouseleave",
                function() {

                    // 기존 기능 그대로
                    changeRegionColor(
                        region,
                        defaultColor
                    );

                }
            );


            // =========================
            // 클릭
            // =========================

            prefecture.addEventListener(
                "click",
                function() {

                    console.log(
                        "선택한 지역:",
                        regionNames[region]
                    );


                    alert(
                        regionNames[region]
                    );

                }
            );


            // =========================
            // 커서
            // =========================

            prefecture.style.cursor =
                "pointer";

        });

    });

});
