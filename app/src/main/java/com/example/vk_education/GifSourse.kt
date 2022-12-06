import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_education.Model.Gif
import com.example.vk_education.Network.GifApi
import com.example.vk_education.Provider.GifProvider
import retrofit2.HttpException
import java.io.IOException

class GifSourse : PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int?
    {
        return state.anchorPosition
    }
//
//    override val keyReuseSupported: Boolean
//        get() = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return try {
            val nextPage = params.key ?: 25
            val userList = GifProvider(GifApi.create()).getGifs("gZ3s6VZmS8vgIrMc1yPvEg12k3Yzdo1P","bat", offset = nextPage.toString())
            LoadResult.Page(
                data = userList,
                prevKey = if (nextPage == 25) null else nextPage - 25,
                nextKey = if (userList.isEmpty()) null else nextPage + 25
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}