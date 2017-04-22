package com.google.gson;

import com.google.gson.reflect.TypeToken;
import daum.cafe.service.svccafeinfo.entity.Tcategory;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class GsonTest {
	@Test
	public void test() throws Exception {
		Type listType = new TypeToken<Tcategory[]>() {
		}.getType();
		Tcategory[] categorys = new Gson().fromJson(getJson(), listType);

		assertNotNull(categorys);
		// {"cateid":"1Io","catename":"회사모임","catedesc":"friend_cowork","subcateyn":"1","parcateid":"o","todaycafenum":0,"cafenum":10} 을 찾음.
		Tcategory friendCoworkCategory = Arrays.stream(categorys).filter(c -> Objects.equals(c.cateid, "1Io")).findFirst().orElse(null);
		assertThat(friendCoworkCategory.catename, is("회사모임"));
		assertThat(friendCoworkCategory.parcateid, is("o"));
	}

	private InputStreamReader getJson() throws UnsupportedEncodingException {
		return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("category.json"), "UTF-8");
	}
}

/**
 * category.json 원본(IcategoryHandler#selectAll 의 결과)
 * 
 * <pre>
 * CATEID CATENAME                       CATEDESC                                           SUBCATEYN PARCATEID TODAYCAFENUM  CAFENUM
 * ------ ------------------------------ -------------------------------------------------- --------- --------- ------------ --------
 * 1      컴퓨터/인터넷                   computer                                                                          0        0
 * 1d     통신/네트워크                   computer_network                                   1         1                    1       66
 * 1e     프로그래밍 언어                  computer_pl                                        1         1                    2       20
 * 1f     인터넷 일반                     computer_internet                                  1         1                    1      120
 * 1g     하드웨어                        computer_hardware                                  1         1                   -1       13
 * 1h     소프트웨어                      computer_software                                  1         1                   -1       17
 * 1i     그래픽/디자인                   computer_graphic                                   1         1                    2       47
 * 1j     운영체제                        computer_os                                        1         1                    0       25
 * 1k     보안                           computer_security                                  1         1                    0        9
 * 1l     멀티미디어                      computer_multimedia                                1         1                    3       10
 * 1m     기술동향                        computer_trend                                     1         1                    2       63
 * 1n     정보통신 비즈니스                computer_intbusiness                               1         1                    0       14
 * 1o     컴퓨터 일반                     computer_compureg                                  1         1                    3      282
 * 1p     관련모임                        computer_comcircle                                 1         1                    4      152
 * 2      영화                           movie                                                                             0        0
 * 3F     배우/감독                       movie_actor                                        1         2                    1       15
 * 3G     장르별                         movie_genre                                        1         2                    1      -12
 * 3H     영화별                         movie_film                                         1         2                    1       49
 * 3I     비디오                         movie_video                                        1         2                    4       50
 * 3J     영화창작                        movie_making                                       1         2                    0        3
 * 3K     영화음악                        movie_music                                        1         2                    0        6
 * 3L     주제/이슈                       movie_issue                                        1         2                    0        0
 * 3M     영화일반                        movie_general                                      1         2                    1       40
 * 3N     영화평/이론                     movie_review                                       1         2                    3       12
 * 3O     영화정보                        movie_info                                         1         2                    9       35
 * 3      음악                           music                                                                             0        0
 * 4r     클래식                         music_classic                                      1         3                    0       41
 * 4s     가요/국내                       music_korean                                       1         3                    2       68
 * 4t     Pop/Rock                       music_pop                                          1         3                    1       15
 * 4u     장르별                         music_genre                                        1         3                    0       22
 * 4v     가수/음악가                     music_singer                                       1         3                    5       31
 * 4w     작곡/작사/노래                  music_writer                                       1         3                    3       57
 * 4x     악기                           music_instru                                       1         3                    1       19
 * 4y     음악일반                        music_general                                      1         3                    0       82
 * 4z     개인밴드/모임                   music_band                                         1         3                    2       41
 * 50     주제별                         music_subject                                      1         3                    5       71
 * 51     풍물/민속음악                   music_folk                                         1         3                    0       15
 * 4      문학/예술                       art                                                                              -1       -2
 * 6T     소설                           art_fiction                                        1         4                    7      101
 * 6U     시                             art_poem                                           1         4                    3       47
 * 6V     판타지/SF                      art_sf                                             1         4                    0       48
 * 6W     문학작가/작품                   art_author                                         1         4                   -1        8
 * 6X     문학창작                        art_write                                          1         4                    1       35
 * 6Y     문학일반                        art_liter                                          1         4                    4       61
 * 6Z     패션                           art_fashion                                        1         4                    0       10
 * 6a     디자인/건축                     art_design                                         1         4                    1       23
 * 6b     미술/조각/사진                  art_paint                                          1         4                    8      114
 * 6c     독서/토론                       art_read                                           1         4                    0       35
 * 6d     연극/공연                       art_play                                           1         4                    0       26
 * 6e     관련모임                        art_circle                                         1         4                    0       27
 * 6f     기타                           art_other                                          1         4                    2       62
 * 6g     수필                           art_essay                                          1         4                    1        9
 * 6h     댄스/힙합                       art_dance                                          1         4                    0        8
 * 5      팬카페                         fan                                                                               0        0
 * 85     국내가수/음악가                  fan_dom                                            1         5                    4       84
 * 86     국외가수/음악가                  fan_int                                            1         5                    2       29
 * 87     국내배우                        fan_actdom                                         1         5                    4       21
 * 88     국외배우                        fan_actint                                         1         5                    0       24
 * 89     스포츠 선수                     fan_sport                                          1         5                   -3       14
 * 8A     연예인                         fan_ent                                            1         5                   11      112
 * 8B     기타                           fan_other                                          1         5                    3      193
 * 6      게임                           game                                                                              0        0
 * 9h     스타크래프트                    game_starcraft                                     1         6                    2      136
 * 9i     일반                           game_general                                       1         6                   -1      484
 * 9j     온라인게임                      game_network                                       1         6                    1      137
 * 9k     비디오게임                      game_video                                         1         6                    5       51
 * 9l     길드/클랜                       game_clan                                          1         6                   16      626
 * 9m     대항해시대                      great_cruise                                       1         6                    0       13
 * 8      스포츠/레저                     sports                                                                            0        0
 * Cv     여행일반                        sports_travel                                      1         8                    0       19
 * Cw     국내여행                        sports_domtravel                                   1         8                    1       18
 * Cx     해외여행                        sports_inttravel                                   1         8                    5       38
 * Cy     야구                           sports_baseball                                    1         8                    2       14
 * Cz     축구                           sports_football                                    1         8                    1       79
 * D0     스포츠 기타                     sports_othersport                                  1         8                    5      165
 * D1     자동차                         sports_auto                                        1         8                   10       68
 * D3     레저 기타                       sports_leisureother                                1         8                    3       85
 * D4     농구                           sports_basketball                                  1         8                    0        9
 * D5     볼링/당구                       sports_bowling                                     1         8                    0       18
 * D6     태권도/택견                     sports_taekwondo                                   1         8                    0       27
 * D7     검도/유도/기타                  sports_other                                       1         8                    0       19
 * D8     등산/산악                       sports_climb                                       1         8                    1       78
 * 9      취미                           hobby                                                                             0        0
 * EX     수집                           hobby_collect                                      1         9                    3       51
 * EY     만들기                         hobby_make                                         1         9                    4      127
 * EZ     일반                           hobby_general                                      1         9                   11      512
 * Ea     키우기                         hobby_grow                                         1         9                    5       97
 * A      대학/대학원                     univ                                                                              0      150
 * G9     대학교                         univ_school                                        1         A                    1       69
 * GA     학과                           univ_depart                                        1         A                   11      148
 * GB     전공/주제별                     univ_subject                                       1         A                    0      152
 * GC     대학생활                        univ_collegelife                                   1         A                    3       28
 * GD     대학원                         univ_grad                                          1         A                   -1       75
 * GE     동아리/학회                     univ_circle                                        1         A                    0       51
 * GF     학번                           univ_year                                          1         A                    1       10
 * B      초중고교                        school                                                                            0        0
 * Hl     10대                           school_teen                                        1         B                    4       86
 * Hm     학교모임                        school_highschool                                  1         B                    1      564
 * Hn     동아리                         school_circle                                      1         B                    0       59
 * C      교육/외국어                     education                                                                         0        0
 * JN     영어                           education_english                                  1         C                    0       87
 * JO     일어                           education_japanese                                 1         C                    1        9
 * JP     외국어                         education_foreign                                  1         C                    2       17
 * JQ     고시/시험                       education_exam                                     1         C                    3       50
 * JR     교사                           education_teacher                                  1         C                    0      103
 * JS     교육일반                        education_general                                  1         C                    3      291
 * JT     학부모                         education_parents                                  1         C                    0       25
 * D      과학/인문                       science                                                                           0        0
 * Kz     과학                           science_sci                                        1         D                    0       17
 * L0     공학                           science_eng                                        1         D                    0        6
 * L1     자연/환경                       science_env                                        1         D                    0       12
 * L2     인문                           science_lit                                        1         D                    0        7
 * L3     철학                           science_phil                                       1         D                    0        3
 * L4     천문학                         science_astro                                      1         D                    1       17
 * E      종교                           religion                                                                          0        0
 * Mb     기독교                         religion_christ                                    1         E                   11      429
 * Mc     불교                           religion_buddism                                   1         E                    0       31
 * Md     가톨릭                         religion_catholic                                  1         E                    1       59
 * Me     종교일반                        religion_general                                   1         E                   -1        4
 * Mf     기타종교                        religion_other                                     1         E                    0        9
 * Mg     종교모임                        religion_meet                                      1         E                    0        6
 * Mh     원불교                         religion_won                                       1         E                    0        2
 * Mi     봉사                           religion_serve                                     1         E                    0        1
 * F      지역/고향                       area                                                                              0        0
 * OD     서울                           area_seoul                                         1         F                    0       40
 * OE     부산                           area_pusan                                         1         F                    0       24
 * OF     대구                           area_taegu                                         1         F                    2        7
 * OG     광주                           area_kwangju                                       1         F                    0        6
 * OH     인천                           area_inchon                                        1         F                    1       13
 * OI     대전                           area_taejun                                        1         F                    0       23
 * OJ     울산                           area_woolsan                                       1         F                    0       32
 * OK     강원도                         area_kangwon                                       1         F                    0        4
 * OL     경기도                         area_kyunggi                                       1         F                    0       27
 * OM     경상도                         area_kyungsang                                     1         F                    0       43
 * ON     전라도                         area_chunlla                                       1         F                    0       36
 * OO     제주도                         area_cheju                                         1         F                    0       10
 * OP     충청도                         area_choongchung                                   1         F                    1       18
 * OR     해외                           area_forgeign                                      1         F                    1       30
 * OS     기타                           area_other                                         1         F                    0       15
 * G      생활/건강                       life                                                                              0        0
 * Pp     요리                           life_cook                                          1         G                    0       18
 * Pq     건강                           life_health                                        1         G                    2      111
 * Pr     결혼                           life_wedding                                       1         G                    1       16
 * Ps     인테리어                        life_interior                                      1         G                    1       24
 * Pt     육아/가족                       life_child                                         1         G                    0       44
 * Pu     기타                           life_other                                         1         G                    1       44
 * H      여성                           women                                                                             0        0
 * RR     주제별                         women_subject                                      1         H                    1       12
 * RS     모임/연령별                     women_meet                                         1         H                    1        3
 * RT     결혼/성                        women_sex                                          1         H                    1       14
 * RU     육아/자녀                       women_child                                        1         H                    0       34
 * RV     요리/인테리어                   women_cook                                         1         H                    0       10
 * RW     패션/미용                       women_fashion                                      1         H                    0       38
 * RX     다이어트/건강                   women_diet                                         1         H                    0       13
 * J      경제/금융                       finance                                                                           0        0
 * Uf     증권                           finance_stock                                      1         J                    0       13
 * Ug     벤처/창업                       finance_venture                                    1         J                    1       35
 * Uh     이론/정보                       finance_info                                       1         J                    1       15
 * Ui     거래소                         finance_exchange                                   1         J                    0        4
 * Uj     코스닥                         finance_kosdaq                                     1         J                    0        1
 * Uk     장외시장                        finance_off                                        1         J                    0        2
 * Ul     부동산                         finance_reality                                    1         J                    0       67
 * Um     보험                           finance_insure                                     1         J                    0        9
 * Un     재테크                         finance_reinvest                                   1         J                    1       19
 * Uo     기타                           finance_other                                      1         J                    5       20
 * K      정치/사회                       politics                                                                          0        0
 * Kp     시사                           politics_sisa                                      1         K                    1       11
 * Kq     이슈/화제                       politics_issue                                     1         K                    1       33
 * WH     정치                           politics_pol                                       1         K                    0        8
 * WI     법률                           politics_law                                       1         K                    0        9
 * L      방송/연예                       tv                                                                                0        0
 * Xt     TV                             tv_tv                                              1         L                    1       55
 * Xu     RADIO                          tv_radio                                           1         L                    0        5
 * Xv     방송일반                        tv_general                                         1         L                    3       41
 * Xw     인터넷방송/광고                  tv_webtv                                           1         L                    0       13
 * M      만화/애니메이션                  ani                                                                               0        0
 * ZV     일반                           ani_general                                        1         M                    1      228
 * ZW     창작                           ani_make                                           1         M                    0       45
 * ZX     주제/작품별                     ani_subject                                        1         M                    0       79
 * ZY     코스프레                        ani_cosplay                                        1         M                    0       30
 * N      동창/동문                       alum                                                                              0        0
 * b7     초등학교                        alum_ele                                           1         N                    5     1089
 * b8     중학교                         alum_mid                                           1         N                    2      264
 * b9     고등학교                        alum_high                                          1         N                    7      146
 * bA     대학교                         alum_univ                                          1         N                    0      104
 * bB     기타                           alum_other                                         1         N                    1       81
 * bC     학원                           alum_aca                                           1         N                    0       11
 * P      군대                           army                                                                              0        0
 * z1     육군                           land_army                                          1         P                    0      142
 * z2     공군                           airforce                                           1         P                    0       28
 * z3     해군                           navy                                               1         P                    0       12
 * Q      e-마을                         evillage                                                                          0        0
 * Q1     e-전라남도                      evillage_jnamdo                                    1         Q                    0       37
 * Q2     e-경상북도                      evillage_gyeongbuk                                 1         Q                    0       14
 * Q3     e-충청남도                      evilage_choongnam                                  1         Q                    0       12
 * o      친목                           friend                                                                            0        0
 * 1If    나이                           friend_age                                         1         o                    1       50
 * 1Ig    직업                           friend_job                                         1         o                    8      304
 * 1Ih    친구/펜팔                       friend_1                                           1         o                   24   391953
 * 1Ii    사랑/만남                       friend_love                                        1         o                   23      231
 * 1Ij    대화/휴식                       friend_chat                                        1         o                   32      791
 * 1Ik    기타                           friend_others                                      1         o                   22      597
 * 1Il    종친                           friend_name                                        1         o                    0       76
 * 1Im    군대                           friend_mil                                         1         o                    3       57
 * 1In    연인/가족                       friend_family                                      1         o                    0        9
 * 1Io    회사모임                        friend_cowork                                      1         o                    0       10
 * </pre>
 */
