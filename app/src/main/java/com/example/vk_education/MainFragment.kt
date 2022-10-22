package com.example.vk_education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                FragmentComposeView()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


@Preview
@Composable
fun FragmentComposeView() {
    Row {
        RecyclerImageList()
        RecyclerImageList()
        RecyclerImageList()
    }
}

@Preview
@Composable
fun RecyclerImageList() {
    val provider  = DataProvider()
    Column {
        Row(
            modifier = Modifier
                .height(750.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                .align(CenterHorizontally)
        ) {
            val images = rememberUpdatedState(newValue = provider.imagelist).value
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = images,
                    itemContent = {
                        ImageListItem(image = it)
                    })
            }
        }
        Row(
            modifier = Modifier
                .height(790.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                .align(CenterHorizontally)
        ) {
            Button(onClick = {
                provider.imagelist.add(Image(provider.i))
                provider.i++
            }) {
                Text(text = "Add")
            }
        }
    }
}

//@Preview
//@Composable
//fun ImageList() {
//    val imagelist = mutableListOf<Image>()
//    for (i in 0..200) {
//        imagelist.add(Image(i))
//    }
//    val images = remember { imagelist }
//    LazyColumn(
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//    ) {
//        items(
//            items = images,
//            itemContent = {
//                ImageListItem(image = it)
//            })
//    }
//}