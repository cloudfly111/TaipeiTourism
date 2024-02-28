# 旅遊臺北 Android App


### build 環境：
- IDE：Android Studio Chipmunk | 2021.2.1 Patch 1
- Kotlin version：1.7.10

### 架構：MVVM

## 功能流程
1. 首頁：
- 顯示最新消息和遊憩景點清單
- App bar 左邊按紐可跳出 Dialog 切換語系
- 點選遊憩景點清單項目可進入景點頁面

2. 景點頁面：
- App bar 標題為景點名稱（隨語系改變）
- 中間可左右滑動切換景點圖片
- 點選圖片下方按鈕可進入 WebView 頁面開啟景點官方網站

3. WebView 頁面：
- App bar 標題為景點名稱（隨語系改變）
- WebView 開啟景點官方網站

## 使用套件
- 由網址載入圖片： Glide 
- API 串接：Retrofit
- 異步處理：Coroutine 

## [Demo 影片](https://youtu.be/fw0FBS3vMDY?si=7U2SOWNlEmM8ADHK)

