# 🧭 Guía: Creación de un Tópico y Suscripción en Pub/Sub (Emulador Local)

Este proyecto incluye instrucciones para crear manualmente un **tópico** y una **suscripción** en el emulador local de **Google Pub/Sub**, utilizando **PowerShell** en Windows.

Se agrego variable en Intellij PUBSUB_EMULATOR_HOST=localhost:8085 para correr.

---

## 1️⃣ Crear el Tópico y la Suscripción (PowerShell)

Copia y pega los siguientes comandos **uno por uno** en tu terminal de Windows.

### 🅰️ Paso A: Crear el tópico

```powershell
Invoke-RestMethod -Uri "http://localhost:8085/v1/projects/test-project/topics/test-topic" -Method Put
```

### 🅱️ Paso B: Crear la suscripción

```powershell
$body = @{ topic = "projects/test-project/topics/test-topic" } | ConvertTo-Json
Invoke-RestMethod -Uri "http://localhost:8085/v1/projects/test-project/subscriptions/test-subscription" -Method Put -Body $body -ContentType "application/json"
```

---

## 2️⃣ Verificar que los recursos existen

Ejecuta el siguiente comando para **listar las suscripciones** existentes:

```powershell
Invoke-RestMethod -Uri "http://localhost:8085/v1/projects/test-project/subscriptions"
```

Deberías recibir una respuesta similar a:

```json
{
  "subscriptions": [
    {
      "name": "projects/test-project/subscriptions/test-subscription"
    }
  ]
}
```

Enviar mensaje directo al publisher para probar suscriptor:

```powershell
# 1. Preparamos el mensaje en Base64
$message = "Mensaje directo al emulador"
$base64Text = [Convert]::ToBase64String([System.Text.Encoding]::UTF8.GetBytes($message))

# 2. Creamos el cuerpo JSON
$body = @{
    messages = @(
        @{ data = $base64Text }
    )
} | ConvertTo-Json

# 3. Enviamos al emulador
Invoke-RestMethod -Uri "http://localhost:8085/v1/projects/test-project/topics/test-topic:publish" -Method Post -Body $body -ContentType "application/json"
```

---

## 🧩 Notas

- Asegúrate de que el **Emulador de Pub/Sub** esté corriendo en `localhost:8085`.
- Cambia `test-project`, `test-topic`, y `test-subscription` según tu entorno o nombres deseados.
- Estos comandos funcionan tanto en **Windows PowerShell** como en **PowerShell 7+**.
