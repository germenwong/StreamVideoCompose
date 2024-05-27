package com.hgm.streamvideocompose.connect


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ConnectScreen(
      state: ConnectState,
      onAction: (ConnectAction) -> Unit
) {

      Column(
            modifier = Modifier
                  .fillMaxSize()
                  .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
            Text(text = "Choose a name", fontSize = 18.sp)
            TextField(
                  value = state.name,
                  onValueChange = { onAction(ConnectAction.OnNameChange(it)) },
                  placeholder = { Text(text = "Name") },
                  modifier = Modifier.fillMaxWidth()
            )
            Button(
                  onClick = { onAction(ConnectAction.OnConnectClick) },
                  modifier = Modifier.align(Alignment.End)
            ) {
                  Text(text = "Connect")
            }
            if (state.errorMessage != null) {
                  Text(text = state.errorMessage, color = MaterialTheme.colorScheme.error)
            }
      }
}

@Preview
@Composable
fun ConnectScreenPreview() {
      ConnectScreen(state = ConnectState(), onAction = {})
}

