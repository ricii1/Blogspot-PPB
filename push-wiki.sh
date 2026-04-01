#!/bin/bash

# Script untuk push wiki ke GitHub
# Usage: ./push-wiki.sh "commit message"

WIKI_DIR="Blogspot-PPB.wiki"
MESSAGE="${1:-Update wiki}"

cd "$WIKI_DIR" || exit 1

echo "Updating wiki..."

git add .
git commit -m "$MESSAGE"
git push origin master

if [ $? -eq 0 ]; then
    echo "Wiki berhasil di-push ke GitHub!"
else
    echo "Gagal push wiki"
    exit 1
fi
