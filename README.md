<div align="center">

<img src="assets/banner.svg" alt="XintuChat" width="100%">

# XintuChat

**QQ 群 ↔ 游戏聊天互联** —— QQ 群消息实时转进游戏聊天（带格式与敏感词过滤），游戏内 `/reply` 直接回复群成员。

![Platform](https://img.shields.io/badge/Platform-Spigot%20%2F%20Paper-62bda8?style=flat-square&logo=minecraft&logoColor=white)
![Minecraft](https://img.shields.io/badge/MC-1.18.x%2B-8a9a5b?style=flat-square)
![Java](https://img.shields.io/badge/Java-17-f89820?style=flat-square&logo=openjdk&logoColor=white)
![Depends](https://img.shields.io/badge/depend-XintuWhiteList-e2b90f?style=flat-square)

[功能](#-功能) · [命令](#-命令) · [配置](#️-配置)

</div>

## ✨ 功能

- **双向聊天桥接**：`GuildMessageListener` 接收 QQ 群消息，按 `chat-format` 格式（`🐧QQ群 | …`）广播进游戏聊天
- **敏感词过滤**：`SensitiveFilter` 三层过滤 —— 云词库（远程 JSON 词库，可挂多个源）+ 本地词库 + 标点绕过识别（忽略分隔符后再匹配），命中替换为 `Replacement`
- **词库白名单**：`Ignored` 列出云词库中不该当敏感词处理的词
- **游戏内回复**：`/reply <formId> <内容>` 定向回复某位群成员

## 📜 命令

| 命令 | 说明 |
|------|------|
| `/reply <formId> <内容>` | 回复指定群成员 |

## ⚙️ 配置

```yaml
Enable: true

Cloud-Thesaurus:
  Enabled: true
  Ignored: ['nt']
  Urls:
    - 'https://raw.githubusercontent.com/Yurinann/Filter-Thesaurus-Cloud/main/database.json'

Local:
  - NMSL
  - fuck

Ignored-Punctuations: ['!','.',',','#','~',' ', '！','。','，','…']   # 绕过符号表
Replacement: '*'
chat-format: "🐧§cQQ群 §7| §f"
```

## 🔨 构建

```bash
./gradlew shadowJar
```

> 配套使用：[XintuWhiteList](https://github.com/windy664/XintuWhiteList)（QQ 验证码白名单）
