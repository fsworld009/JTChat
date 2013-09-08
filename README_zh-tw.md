JTChat
===========
自製IRC Client, 設計給Jtv/Twitch實況主抓取聊天室畫面使用
目前還沒有欄位防呆設計，請不要回報因設定欄位格式不合(ex. Port填英文)所產生的錯誤 謝謝

系統需求
===========
Java Runtime Environment (7 or above)

改版紀錄
===========
 - 2013.09.08
   - 支援Jtv/Twitch清除聊天室指令 (/clear)
   - 支援Jtv/Twitch清除被ban使用者的留言 (/ban或/timeout)
   - 可手動設定聊天室位置
   - 自動儲存/載入上次執行程式的設定
   - 新增繁體中文介面 (從Setting→Profile→Language中選擇)
 - 2013.08.31
   - 提示使用者被ban/timeout
   - 帳號首字大寫
   - 修正連線/離線按紐有時功能相反的問題
   - 增加聊天室畫面邊框大小設定
   - 支援滑鼠調整聊天室大小
 - 2013.08.27-2
   - 修正過度使用CPU問題
   - 禁止送出空白訊息
 - 2013.08.27
   - 修正非英文字元無法正常顯示/傳送問題
 - 0.0
   - 第一版
   
IRC設定說明
========
暱稱為你在Jtv/Twitch的帳號
伺服器密碼為你在Jtv/Twitch的帳號密碼

連到Jtv帳號ajtvuser的聊天室,
伺服器位址為 ajtvuser.jtvirc.com
伺服器Port為80、443、6667選一 (推薦443)
聊天頻道為 #ajtvuser

連到Jtv帳號atwitchuser的聊天室,
伺服器位址為 irc.twitch.tv
伺服器Port為80、443、6667選一 (推薦443)
聊天頻道為 #atwitchuser

授權條款
========
JTChat 採用 GNU GPL v3 做為授權條款

下載
==========
https://mega.co.nz/#F!udtRiARY!EyG9WCrm8FtbJwLBKbS94A

原始碼
==========
JTChat(GitHub):
https://github.com/fuunkaosekai/JTChat

關於作者
==========
雖小臉世界
http://fuunkao-sekai.blogspot.com

Reference
==========
NwFontChooserS.java is from
http://www.java2s.com/Code/Java/Swing-Components/FontChooserbyNoahw.htm

JConfirmedFileChooser is from
http://stackoverflow.com/questions/3651494/jfilechooser-with-confirmation-dialog

JTextPane Line wrap fixes under jre7 is from
http://java-sl.com/tip_letter_wrap_java7.html
