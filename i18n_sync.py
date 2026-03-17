import os

# 定义五国语言的准确翻译（直接硬编码，保证不空）
TRANSLATIONS = {
    "values": ["SQL Formatter Pro", "Paste SQL here...", "Format", "Enter SQL", "Error", "Success"],
    "values-zh-rCN": ["SQL 格式化助手", "在此粘贴 SQL...", "一键美化", "请输入 SQL", "格式化失败", "格式化成功"],
    "values-ja": ["SQL整形プロ", "ここにSQLを貼り付け", "整形する", "SQLを入力してください", "整形に失敗しました",
                  "整形に成功しました"],
    "values-ko": ["SQL 포맷터 프로", "여기에 SQL 붙여넣기", "포맷하기", "SQL을 입력하세요", "포맷 실패", "포맷 성공"],
    "values-de": ["SQL Formatierer", "SQL hier einfügen", "Formatieren", "Bitte SQL eingeben",
                  "Formatierung fehlgeschlagen", "Erfolgreich formatiert"],
    "values-en": ["SQL Formatter Pro", "Paste SQL here...", "Format", "Enter SQL", "Error", "Success"]
}

KEYS = ["app_name", "hint_sql", "btn_format", "msg_empty", "msg_error", "msg_success"]
BASE_PATH = "app/src/main/res"


def generate():
    for folder, values in TRANSLATIONS.items():
        target_dir = os.path.join(BASE_PATH, folder)
        if not os.path.exists(target_dir):
            os.makedirs(target_dir)

        # 严格的 XML 格式
        content = '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n'
        for i in range(len(KEYS)):
            content += f'    <string name="{KEYS[i]}">{values[i]}</string>\n'
        content += '</resources>'

        with open(os.path.join(target_dir, "strings.xml"), "w", encoding="utf-8") as f:
            f.write(content)
        print(f"✅ 处理完成: {folder}/strings.xml")


if __name__ == "__main__":
    generate()