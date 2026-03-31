package com.example.libconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors defined inline — no external import needed
private val DeepTeal       = Color(0xFF2E8B6A)
private val SageGreen      = Color(0xFF5BAB7E)
private val LightSage      = Color(0xFFE8F5E3)
private val PaleMint       = Color(0xFFD4F0E8)
private val MintWhite      = Color(0xFFF0FAF5)
private val TextDark       = Color(0xFF1A5C3A)
private val TextMid        = Color(0xFF6A9E80)
private val TextMuted      = Color(0xFFA0C8B0)
private val TextLight      = Color(0xFFA8D8BE)
private val AvatarGreenA   = Color(0xFFC0E8D4)
private val AvatarTextA    = Color(0xFF1A6A42)
private val AvatarGreenB   = Color(0xFFB8E2C8)
private val AvatarTextB    = Color(0xFF155438)
private val AvatarGreenC   = Color(0xFFD4F0E8)
private val AvatarTextC    = Color(0xFF0F4D35)
private val NavBarBorder   = Color(0xFFE0F0E8)
private val CardBorder     = Color(0xFFE8F5E3)
private val DocIconBg      = Color(0xFFE8F5E3)
private val DocIconFold    = Color(0xFFC6E8D4)
private val ProgressBg     = Color(0xFFE8F5E3)
private val ProgressFill   = Color(0xFF3A9E6E)
private val HeaderBg       = Color(0xFF2E8B6A)
private val BrandText      = Color(0xFFD8F5E8)
private val GreetingText   = Color(0xFFA8D8BE)
private val BrandDot       = Color(0xFF8FDBAE)
private val White          = Color(0xFFFFFFFF)
private val HomeBar        = Color(0xFFC0D8C8)

@Composable
fun HomeScreen(
    book: LastReadBook = sampleBook,
    feed: List<FeedItem> = sampleFeed
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSage)
            .systemBarsPadding()
    ) {
        LibConnectHeader(userName = "Aritra")
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            LastReadCard(
                book = book,
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxWidth()
            )
            SocialFeedCard(
                items = feed,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
        LibConnectNavBar()
    }
}

@Composable
fun LibConnectHeader(userName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBg)
            .padding(start = 24.dp, end = 24.dp, top = 10.dp, bottom = 18.dp)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "LibConnect",
                color = BrandText,
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.5).sp
            )
            Spacer(Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(BrandDot)
                    .align(Alignment.Bottom)
            )
        }
        Spacer(Modifier.height(2.dp))
        Text(
            text = "Good morning, $userName",
            color = GreetingText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.04.sp
        )
    }
}

@Composable
fun LastReadCard(book: LastReadBook, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(White)
    ) {
        CardHeaderRow(label = "CONTINUE READING", actionText = "Open")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            DocIcon()
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    color = TextDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "${book.author} · ${book.format} · ${book.totalPages} pages",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(Modifier.height(7.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(ProgressBg)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(book.progressPercent / 100f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(ProgressFill)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${book.progressPercent}% complete · ${book.chapter}",
                    color = TextMuted,
                    fontSize = 10.sp
                )
            }
        }
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            book.tags.forEachIndexed { i, tag ->
                TagChip(
                    label = tag,
                    bg = if (i % 2 == 0) LightSage else PaleMint,
                    fg = if (i % 2 == 0) SageGreen else DeepTeal
                )
            }
        }
    }
}

@Composable
fun SocialFeedCard(items: List<FeedItem>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(White)
    ) {
        CardHeaderRow(label = "SOCIAL FEED", actionText = "See all")
        Column(modifier = Modifier.weight(1f)) {
            items.forEachIndexed { index, item ->
                FeedRow(item = item)
                if (index < items.lastIndex) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MintWhite)
                    )
                }
            }
        }
    }
}

@Composable
fun FeedRow(item: FeedItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        val (bg, fg) = when (item.initials.first()) {
            'S' -> AvatarGreenA to AvatarTextA
            'M' -> AvatarGreenB to AvatarTextB
            else -> AvatarGreenC to AvatarTextC
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(bg),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.initials,
                color = fg,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                color = TextDark,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(1.dp))
            Text(
                text = item.text,
                color = TextMid,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )
        }
        Text(
            text = item.timeAgo,
            color = TextMuted,
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

sealed class NavItem(val label: String) {
    object Home : NavItem("Home")
    object Library : NavItem("Library")
    object Social : NavItem("SM")
    object Profile : NavItem("Profile")
    object Settings : NavItem("Settings")
}

@Composable
fun LibConnectNavBar(selected: NavItem = NavItem.Home) {
    val items = listOf(
        NavItem.Home,
        NavItem.Library,
        NavItem.Social,
        NavItem.Profile,
        NavItem.Settings
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(NavBarBorder)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                NavBarItem(item = item, isActive = item == selected)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(HomeBar)
            )
        }
    }
}

@Composable
fun NavBarItem(item: NavItem, isActive: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(if (isActive) DeepTeal else Color.Transparent)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = navSymbol(item),
            color = if (isActive) DeepTeal else TextLight,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = item.label,
            color = if (isActive) DeepTeal else TextLight,
            fontSize = 9.sp,
            fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
        )
    }
}

fun navSymbol(item: NavItem): String = when (item) {
    NavItem.Home -> "⌂"
    NavItem.Library -> "⊟"
    NavItem.Social -> "⊞"
    NavItem.Profile -> "◉"
    NavItem.Settings -> "⚙"
}

@Composable
fun CardHeaderRow(label: String, actionText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = SageGreen,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(LightSage)
                .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
            Text(
                text = actionText,
                color = SageGreen,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(CardBorder)
    )
}

@Composable
fun DocIcon() {
    Box(
        modifier = Modifier
            .width(42.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(DocIconBg)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.TopEnd)
                .background(DocIconFold)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 18.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            listOf(22.dp, 16.dp, 20.dp, 13.dp).forEach { w ->
                Box(
                    modifier = Modifier
                        .width(w)
                        .height(2.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(SageGreen)
                )
            }
        }
    }
}

@Composable
fun TagChip(label: String, bg: Color, fg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .padding(horizontal = 9.dp, vertical = 3.dp)
    ) {
        Text(
            text = label,
            color = fg,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}