package me.dungngminh.seenowcarouselplayground

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.helper.widget.Carousel
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import me.dungngminh.seenowcarouselplayground.databinding.FragmentHomeBinding
import me.dungngminh.seenowcarouselplayground.databinding.LayoutTopTenBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val images = arrayOf(
        "https://images.unsplash.com/photo-1624976172336-54d765427b6b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=928&q=80",
        "https://images.unsplash.com/photo-1584060622420-0673aad46076?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=928&q=80",
        "https://images.unsplash.com/photo-1609703048009-d3576872b32c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=930&q=80",
        "https://images.unsplash.com/photo-1561299593-7633f311838a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
        "https://images.pexels.com/photos/305070/pexels-photo-305070.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/1545743/pexels-photo-1545743.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.unsplash.com/photo-1628519592419-bf288f08cef5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
        "https://images.unsplash.com/photo-1629450646456-b7a01cdec01a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=930&q=80",
        "https://images.unsplash.com/photo-1573074556015-71d2140a6372?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=930&q=80",
        "https://images.unsplash.com/photo-1627070160373-74a3ca062e1d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSlider()
    }

    private fun setupSlider() {


        binding.layoutTopTen.slider.setAdapter(object : Carousel.Adapter {

            override fun count(): Int = images.size

            override fun populate(view: View, index: Int) {
                val imageView = (view as? ViewGroup)?.getChildAt(0)
                if (imageView is AppCompatImageView) {
                    Glide.with(view)
                        .asBitmap()
                        .load(images[index])
                        .into(createCustomTarget(imageView))
                }
                view.setOnClickListener {
                    Log.d("HomeFragment", "Clicked on $index with image ${images[index]}")
                }
            }

            override fun onNewItem(index: Int) = Unit
        })
    }
    private fun createCustomTarget(view: AppCompatImageView) = object : CustomTarget<Bitmap?>() {
        override fun onResourceReady(
            resource: Bitmap,
            transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?,
        ) {
            view.setImageBitmap(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) = Unit
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}