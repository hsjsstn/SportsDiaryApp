package ddwu.com.mobile.finalreport

class GameDto (var photo : Int, var title : String, var date : String, var category : String, var detail : String) {

    override fun toString() = "[$date] $title - $category"
}
