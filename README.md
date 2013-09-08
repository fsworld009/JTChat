JTChat
===========
A IRC Client designed for Jtv/Twitch broadcasters to show their chatroom on their stream.
Currently there is no input checking machanism, so please don't be too hard on my program, thanks.

System requirement
===========
Java Runtime Environment (7 or above)

ChangeLog
===========
 - 2013.09.07
   - support /clear command on Jtv/Twitch
   - support delete messages from banned users (/ban, /timeout command on Jtv/Twitch)
   - support manually set chatroom's position on screen
   - auto save/load last profile
   - add Traditional Chinese support (choose from Setting→Profile→Language)
 - 2013.08.31
   - indicate that a user is banned/timeouttted
   - capitalize first character of nickname
   - fix: sometines Connect/Disconnect button displays incorrectly (text swapped)
   - add border thinkness option
   - support dragging the chatroom
 - 2013.08.27-2
   - fix: high CPU usage
   - prevent from sending empty message
 - 2013.08.27
   - fix: non-English characters do not display correctly
 - 0.0
   - first release
   
IRC Setting Guide
========
Nickname: your account on Jtv/Twitch
Pass: your account's password on Jtv/Twitch

If you'd like to connect to a jtv user ajtvuser's chatroom
Server: ajtvuser.jtvirc.com
Port: 80, 443, or 6667 (Recommand: 443)
Channel: #ajtvuser

If you'd like to connect to a twitch user 連到Jtv帳號atwitchuser的聊天室's chatroom
Server: irc.twitch.tv
Port: 80, 443, or 6667 (Recommand: 443)
Channel: #連到Jtv帳號atwitchuser的聊天室

Licence
========
JTChat 採用 GNU GPL v3 做為授權條款

Download
==========
https://mega.co.nz/#F!udtRiARY!EyG9WCrm8FtbJwLBKbS94A

Source Code
==========
JTChat(GitHub):
https://github.com/fuunkaosekai/JTChat

Author
==========
Fuunkao Sekai
http://fuunkao-sekai.blogspot.com

Reference
==========
NwFontChooserS.java is from
http://www.java2s.com/Code/Java/Swing-Components/FontChooserbyNoahw.htm

JConfirmedFileChooser is from
http://stackoverflow.com/questions/3651494/jfilechooser-with-confirmation-dialog

JTextPane Line wrap fixes under jre7 is from
http://java-sl.com/tip_letter_wrap_java7.html
