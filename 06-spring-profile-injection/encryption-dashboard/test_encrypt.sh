#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./test_encrypt.sh [base_url]
# Example:
#   ./test_encrypt.sh http://localhost:8080/encryption-dashboard

BASE_URL="${1:-http://localhost:8080}"
API_URL="${BASE_URL%/}/api/encrypt"

TEST_FILE="test.txt"
RESPONSE_FILE="encrypt_response.json"
FILEINFO_PART="fileinfo.json"

# 32-byte key (works for AES-256 and is valid raw length for ChaCha20), then Base64-encoded.
RAW_PASSWORD="0123456789abcdef0123456789abcdef"
B64_PASSWORD="$(printf "%s" "$RAW_PASSWORD" | base64 | tr -d '\n')"

# FileSpec.setFromJSON expects an array with first object: [{"filename":"...","password":"..."}]
FILEINFO_JSON="[{\"filename\":\"${TEST_FILE}\",\"password\":\"${B64_PASSWORD}\"}]"

echo "[INFO] Writing ${TEST_FILE}"
cat > "$TEST_FILE" <<'EOF'
This is encryption test content.
EOF

echo "[INFO] Writing ${FILEINFO_PART}"
printf "%s" "$FILEINFO_JSON" > "$FILEINFO_PART"

echo "[INFO] Sending encrypt request to ${API_URL}"
HTTP_CODE="$(curl -sS -o "$RESPONSE_FILE" -w "%{http_code}" \
  -X POST "$API_URL" \
  -F "fileinfo=@${FILEINFO_PART};type=application/json" \
  -F "file=@${TEST_FILE}")"

echo "[INFO] HTTP status: ${HTTP_CODE}"
echo "[INFO] Response body:"
cat "$RESPONSE_FILE"
echo

if [ "$HTTP_CODE" != "200" ]; then
  echo "[FAIL] Non-200 response from server"
  exit 1
fi

if grep -q '"error"' "$RESPONSE_FILE"; then
  echo "[FAIL] Server returned an error payload"
  exit 1
fi

if grep -q '"mode"[[:space:]]*:[[:space:]]*"encrypt"' "$RESPONSE_FILE"; then
  echo "[PASS] Encrypt API responded with mode=encrypt"
else
  echo "[WARN] Response does not clearly include mode=encrypt"
  exit 1
fi
