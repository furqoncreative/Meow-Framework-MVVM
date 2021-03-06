/*
 * Copyright (C) 2020 Hamidreza Etebarian & Ali Modares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.ui.meowwidget.dash

import android.graphics.Color
import android.os.Bundle
import android.view.View
import meow.ktx.dp
import meow.ktx.instanceViewModel
import sample.R
import sample.databinding.FragmentDashBinding
import sample.ui.base.BaseFragment

/**
 * Meow Dash Fragment.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-04-05
 */

class DashFragment : BaseFragment<FragmentDashBinding>() {

    private val viewModel: DashViewModel by instanceViewModel()
    override fun layoutId() = R.layout.fragment_dash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dashView.apply {
            length = 24f.dp()
            gap = 16f.dp()
            thickness = 24f.dp()
            dashColor = Color.BLUE
        }
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

}