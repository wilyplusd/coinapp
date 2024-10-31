package ru.netology.coinapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.coinapp.R
import ru.netology.coinapp.dto.Asset

@Composable
fun AssetDetailsScreen(
    asset: Asset,
) {
    val uriHandler = LocalUriHandler.current

    Column (
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text (
            text = asset.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Text(
            text = "${stringResource(R.string.asset_symbol)}: ${asset.symbol}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Text(
            text = "${stringResource(R.string.asset_price_usd)}: ${String.format("%.2f", asset.priceUsd)}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Text(
            text = "${stringResource(R.string.asset_volume_usd_24h)}: ${String.format("%.2f", asset.volumeUsd24Hr)}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Text(
            text = "${stringResource(R.string.asset_supply)}: ${String.format("%.2f", asset.supply)}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        if (asset.explorer != null) {
            Text(
                text = buildAnnotatedString {
                    withLink(
                        link = LinkAnnotation.Clickable(
                            tag = "TAG",
                            linkInteractionListener = {
                                uriHandler.openUri(asset.explorer)
                            },
                        ),
                    ) {
                        append(asset.explorer)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}

@Preview
@Composable
fun AssetDetailsScreenPreview() {
    AssetDetailsScreen(
        asset = Asset(
            "bitcoin",
            1,
            "BTC",
            "Bitcoin",
            19775850.000000,
            70541.872030,
            1.3950254799935723E12,
            1.1164128822894232E10,
            70541.872030,
            "https://blockchain.info/",
        )
    )
}
