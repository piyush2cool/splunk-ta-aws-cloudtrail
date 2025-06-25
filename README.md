# Splunk TA for AWS CloudTrail (Spring Boot)

A Java-based Splunk Technology Add-On (TA) that integrates AWS CloudTrail logs with Splunk via the HTTP Event Collector (HEC). This add-on fetches CloudTrail logs from an S3 bucket, parses each event, and securely sends them into Splunk for monitoring, security auditing, and compliance insights.

---

## 🚀 Features

- 🔐 Secure integration with AWS S3 (via AWS SDK)
- ⏱️ Scheduled polling at configurable intervals
- 📤 Sends parsed CloudTrail events to Splunk HEC
- 🧠 Compatible with Splunk CIM for ES use cases
- 🛠️ Deployable as a `.spl` Splunk Technology Add-on package

---

## 🧰 Technologies Used

- Java 17
- Spring Boot
- AWS SDK for Java v2
- Apache HTTP Client
- Splunk HEC (HTTP Event Collector)

---

## 📁 Project Structure

```
splunk-ta-aws-cloudtrail/
├── bin/                     # Shell script to run the JAR
├── lib/                     # Contains compiled JAR file
├── default/                 # Splunk input configuration
├── metadata/                # Splunk permissions metadata
├── README/                  # Documentation
├── app.conf                 # Splunk manifest
├── src/                     # Spring Boot source code
├── pom.xml                  # Maven config
└── logback-spring.xml       # Logging config
```

---

## ⚙️ Configuration

### 🔹 `application.properties`

Located in `src/main/resources/application.properties`:

```properties
aws.region=ap-south-1
aws.s3.bucket-name=your-cloudtrail-logs-bucket

splunk.hec.url=https://your-splunk-host:8088/services/collector
splunk.hec.token=YOUR_SPLUNK_HEC_TOKEN

poll.interval.seconds=300
```

---

## 🧪 Build Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/splunk-ta-aws-cloudtrail.git
cd splunk-ta-aws-cloudtrail
```

### 2. Package the JAR

```bash
mvn clean package
```

> Output: `target/splunk-ta-cloudtrail-1.0.0.jar`

---

## 📦 Create `.spl` Add-on for Splunk

1. Place the JAR into `lib/`:
   ```bash
   cp target/splunk-ta-cloudtrail-1.0.0.jar TA-aws-cloudtrail/lib/
   ```

2. Package the add-on:
   ```bash
   cd TA-aws-cloudtrail
   tar -czf ../TA-aws-cloudtrail.spl .
   ```

3. Upload `TA-aws-cloudtrail.spl` via Splunk Web:
   - Go to **Apps > Manage Apps > Install app from file**

---

## 🔄 Add-on Execution in Splunk

- The `inputs.conf` sets up a scripted input that runs every 5 minutes (or as configured).
- `bin/run_integration.sh` starts the Spring Boot app with:
  ```bash
  $SPLUNK_HOME/bin/splunk cmd java -jar $SPLUNK_HOME/etc/apps/TA-aws-cloudtrail/lib/splunk-ta-cloudtrail.jar
  ```

---

## 🔍 Search in Splunk

Once installed and running, view CloudTrail events in Splunk:

```
index=main sourcetype=aws:cloudtrail
```

---

## 🛡 Security Notes

- No AWS credentials are stored in this repo.
- All credentials are expected to be injected via `application.properties` or environment variables.
- Secure your Splunk HEC token and bucket policies accordingly.

---

## 📥 GitHub Release

You can download the packaged `.spl` file from the [Releases](https://github.com/your-username/splunk-ta-aws-cloudtrail/releases) section of this repository.

---

## 🧾 License

This project is licensed under the [MIT License](LICENSE).

---

## ⚠️ Disclaimer

> This is an independently developed add-on by [Piyush Gupta](https://github.com/your-username).  
> It is **not affiliated with or endorsed by Splunk Inc. or Amazon Web Services**.  
> “Splunk” and “CloudTrail” are registered trademarks of their respective owners.

---

## ✉️ Contact

- GitHub: [@piyush2cool](https://github.com/piyush2cool)
- Email: piyushjlbs20016@gmail.com
