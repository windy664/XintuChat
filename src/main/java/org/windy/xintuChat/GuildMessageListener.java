package org.windy.xintuChat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.windy.xingtuAPI.GuildMessageEvent;
import org.windy.xintuWhiteList.XintuWhiteList;
import org.windy.xintuWhiteList.api.WhiteListAPI;

import java.util.List;

public class GuildMessageListener implements Listener {

    @EventHandler
    public void onGuildMessage(GuildMessageEvent event) {
        String message = event.getMessage().trim();
        String formId = event.getFormId();

        // 保存事件，方便回复用
        ReplyCommand.eventMap.put(formId, event);

        if (!message.startsWith(XintuChat.getInstance().getConfig().getString("startsWith","say"))) return;

        String content = message.substring(3).trim();
        if (content.isEmpty()) {
            event.reply("发送内容不能为空！");
            return;
        }

        List<XintuWhiteList.WhiteListEntry> entries = WhiteListAPI.getEntriesByFormId(formId);
        String senderName = entries.isEmpty() ? XintuChat.getInstance().getConfig().getString("entries-Empty","群成员") : entries.get(0).player;

        // 构建主消息部分
        TextComponent chatMessage = new TextComponent(XintuChat.getInstance().getConfig().getString("chat-format","\uD83D\uDC27§cQQ群 §7| §f"));

        TextComponent sender = new TextComponent(senderName + "💬：");
        sender.setColor(ChatColor.WHITE);
        chatMessage.addExtra(sender);

        TextComponent contentComp = new TextComponent(content);
        contentComp.setColor(ChatColor.WHITE);
        chatMessage.addExtra(contentComp);

        // 构建“点击回复”按钮
        TextComponent clickReply = new TextComponent(" [点击回复]");
        clickReply.setColor(ChatColor.YELLOW);
        clickReply.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/xintuchat:reply " + formId + " "));
        clickReply.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("点击回复群聊信息").color(ChatColor.GRAY).create()));

        // 广播
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(chatMessage,clickReply);
        }

    }
}
