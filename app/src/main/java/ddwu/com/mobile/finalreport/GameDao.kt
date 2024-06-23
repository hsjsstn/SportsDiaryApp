package ddwu.com.mobile.finalreport

class GameDao {
    val games = ArrayList<GameDto>()

    init {
        games.add(GameDto(R.mipmap.kia_tigers, "KIA vs SSG", "240612", "baseball", "오랜만에 야구장에서 야구 경기를 봐서 즐거웠다."))
        games.add(GameDto(R.mipmap.ulsan_hd, "울산현대 vs FC서울", "240616", "soccer", "역시 축구는 울산 현대지 울산 최고!"))
        games.add(GameDto(R.mipmap.kia_tigers, "KIA vs 한화", "240621", "baseball", "역시 기아타이거즈가 1등이 맞다."))
        games.add(GameDto(R.mipmap.hle, "BRO vs HLE", "240621", "esports(LOL)", "우리 팀은 꼭 상대팀이 게임을 못해야 이기더라."))
        games.add(GameDto(R.mipmap.hle, "HLE vs KT", "240623", "esports(LOL)", "드디어 내가 알던 우리 팀이 돌아왔다!"))
    }
}