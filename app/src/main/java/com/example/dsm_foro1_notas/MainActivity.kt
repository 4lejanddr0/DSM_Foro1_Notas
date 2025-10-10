package com.example.dsm_foro1_notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import com.example.dsm_foro1_notas.ui.AppNav
import com.example.dsm_foro1_notas.ui.components.FancyBackground
import com.example.dsm_foro1_notas.ui.theme.DSM_Foro1_NotasTheme 

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSM_Foro1_NotasTheme { 
                Box {
                    FancyBackground()
                    AppNav()
                }
            }
        }
    }
}
