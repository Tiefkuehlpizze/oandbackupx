## Please do not use the master branch as starting point! I'm rebasing my personal changes onto it when I'm updating it with the base's master branch and force-push the changes !

# OAndBackupX  (TKP) <img align="left" src="https://raw.githubusercontent.com/Tiefkuehlpizze/OAndBackupX/master/fastlane/metadata/android/en-US/images/icon.png" width="64" />

This is my personal development repository (call it fork). I'm managing my own thoughts and issues here. Feel free to contribute and participate in the discussion.
I'm not creating any official releases. Consider everything from the release section as experimental software. Do not use them these as your reliable backup solution. I'm not giving any warranty to anything. This Readme is modified to meet my personal needs.

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](COC.md) [![Main CI Workflow](https://github.com/machiav3lli/oandbackupx/workflows/Main%20CI%20Workflow/badge.svg?branch=master)](https://github.com/machiav3lli/oandbackupx/actions?query=workflow%3A%22Main+CI+Workflow%22)

OAndBackupX (TKP) is a development fork of the OAndBackup(X) with the aim to build a FOSS backup/restore solution for Android 8+ where old solutions like Titanium Backup started to fail. The original OAndBackup delivered the base and knowledge.
Priority is to use modern APIs and provide the core functionality while constantly improving the code base. Adding new features and reviving broken features is second priority.

Now on functionality of our App:

* It requires root and allows you to backup individual apps and their data.
* Both backup and restore of individual programs one at a time and batch backup and restore of multiple programs are supported.
* Restoring system apps should be possible without requiring a reboot afterwards.
* Backups can be scheduled with no limit on the number of individual schedules and there is the possibility of creating custom lists from the list of installed apps.

OAndBackup requires a rooted phone and allows to backup and restore individual apps and their data. Backup and Restore of Android system apps is supported. However handling system apps depends on whether /system/ can be remounted as writeable though, so this will probably not work for all devices (e.g. htc devices with the security flag on).

## A few personal words of caution

I personally don't see OAndBackupX as production ready as structure of the backup archive is changing a lot. It changed from zip to tar.gz wrapped in AES cipher, directories have been removed and the structure has been flattened. Heavy refactoring is going on and to maintain development speed, upgrade paths don't have any priority right now. Please keep an eye on the release notes and list of planned changes. Usually you can migrate your backup archive by simply deleting it and recreating all backups.

## Installation
### TKP Testing Builds
You can find my testing builds on Github. Please remember, that they are never stable and intended for productive use.
[<img src="badge_github.png" alt="Get it on GitHub" height="80">](https://github.com/machiav3lli/oandbackupx/releases)

### Official Builds
Official releases can be found on these pages However, when my changes are merged, the structure changes also happen on these releases.

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" alt="Get it on F-Droid" height="80">](https://f-droid.org/packages/com.machiav3lli.backup/)
[<img src="https://gitlab.com/IzzyOnDroid/repo/-/raw/master/assets/IzzyOnDroid.png" alt="Get it on IzzyOnDroid" height="80">](https://apt.izzysoft.de/fdroid/index/apk/com.machiav3lli.backup)
[<img src="badge_github.png" alt="Get it on GitHub" height="80">](https://github.com/machiav3lli/oandbackupx/releases)

### Requirements

- Android 8+
- su (rooted Phone)

## Recommendation

A combination with your favourite sync solution (e.g. Syncthing, Nextcloud...)  keeping an encrypted copy of your apps and their data on your server or "stable" device could bring a lot of benefits and save you a lot of work while changing ROMs or just cleaning your mobile device.

## Community

There's a new room on Matrix and a group on Telegram to discuss the development of the App and test new versions:

[OAndBackupX:Matrix.ORG](https://matrix.to/#/!PiXJUneYCnkWAjekqX:matrix.org?via=matrix.org)

You can find me in the official Telegram Group to discuss the development of the App and test new versions:
[t.me/OAndBackupX](https://t.me/OAndBackupX)

Other communities can be found in [machiav3lli's Readme](https://github.com/machiav3lli/oandbackupx).

Our [Code of Conduct](COC.md) applies to the communication in the community same as for all contributers.

## Encryption

You can optionally encrypt your backup AES256 based on a password. This can be enabled in the settings. To restore backups you have set the password first. This way you can store your backups more securely, worrying less about their readability.
Only data from the Android internal storage is encrypted. Due to potential size and access permissions data on the external storage is not encrypted. Games for example download their resources on first launch to this location or your favourite Podcast app might store downloaded audio files.

## Compatibility

OAndBackupX is incompatible with former OAndBackup backups. The backup archive of OAndBackupX is also not stable right now. Please keep an eye on the release notes.

## Goals, TODOs and Ideas
Version 4.0.0 marks another overhaul of the backup process and thus breaks compatibility with previous versions.


- [x] [Backend] Split APK support
- [x] [Backend] Replace zip with tar.gz
- [x] [Backend] Backup Encryption
- [x] [Backend/Feature] Switch to Storage Access Framework (support for external SD Cards, USB-OTG storage and Cloud providers through Android)
- [x] [Backend] Rework Backup folder structure, redesign backup properties file
  - [x] [Backend] Support profiles in Backup Archive
  - [x] [Backend] Support multiple Backup versions
  - [ ] [UI] Support multiple Backup versions
  - [x] [Backend] Refactor `AppInfo`
  -
- [x] [UI/UX] Welcome Screen and initial Setup (Ask for Permissions, Choose Backup Location, Setup Encryption Password)
- [ ] [UI/UX] Offer recommended Settings (INSTALL_FAILED_VERIFICATION_FAILURE issue) on welcome screen

### Further Ideas
- [ ] [Feature] Flashable-ZIP
- [ ] [UI] Advanced Batch Backup/Restore View (like Titanium Backup)
- [ ] [UI/Backend] Detailed Backup Configuration (configure more detailed what should be backed up instead of just "app" + "data" + "external data" and what should be compressed and encrypted (per app?))

### Known Issues/Broken Features

- [Broken Feature] Scheduled Backup does not work reliably, has a malfunctioning UI and can conflict with a running foreground backup
- [Activity Code] App doesn't like to be in background during backup/restore
- [Technical] Preferences handling is weird

#### if you have some java and android knowledge and like to contribute to the project see the following [development document](https://github.com/machiav3lli/oandbackupx/blob/master/DEVDOC.md) to see the goals and where a help is needed. Each contribution and communication in the project community should follow our [Code of Conduct](COC.md).

## Screenshots

### Dark Theme

<p float="left">
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/2.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/3.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/4.png" width="170" />
</p>

### Light Theme

<p float="left">
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/5.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/6.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/7.png" width="170" />
 <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/8.png" width="170" />
</p>

## Building

OAndBackupX is built with gradle, for that you need the android sdk and the newest Android Studio Beta

## Licenses <img align="right" src="agplv3.png" width="64" />

OAndBackupX is licensed under the [GNU's Aferro GPL v3](LICENSE.txt).

App's icon is based on an Icon made by [Catalin Fertu](https://www.flaticon.com/authors/catalin-fertu) from [www.flaticon.com](https://www.flaticon.com)

Placeholders icon foreground made by [Smashicons](https://www.flaticon.com/authors/smashicons) from [www.flaticon.com](https://www.flaticon.com)

## Credits

[Jens Stein](https://github.com/jensstein) for his unbelievably valuable work on OAndBackup.

[Tiefkuehlpizze](https://github.com/Tiefkuehlpizze) for his active contribution to the project.

Features: Split-APK: [Tiefkuehlpizze](https://github.com/Tiefkuehlpizze), Rewrite Shellcommands [Tiefkuehlpizze](https://github.com/Tiefkuehlpizze).

Open-Source libs: [FastAdapter](https://github.com/mikepenz/FastAdapter), [RootBeer](https://github.com/scottyab/rootbeer), [NumberPicker](https://github.com/ShawnLin013/NumberPicker), [Apache Commons](https://commons.apache.org).

### Languages: [<img align="right" src="https://hosted.weblate.org/widgets/oandbackupx/-/287x66-white.png" alt="Übersetzungsstatus" />](https://hosted.weblate.org/engage/oandbackupx/?utm_source=widget)

[<img src="https://hosted.weblate.org/widgets/oandbackupx/-/multi-auto.svg" alt="Übersetzungsstatus" />](https://hosted.weblate.org/engage/oandbackupx/)

The Translations are now being hosted by [Weblate.org](https://hosted.weblate.org/engage/oandbackupx/).

Before that, translations were done analog/offline by those great people:

[Kostas Giapis](https://github.com/tsiflimagas), [Urnyx05](https://github.com/Urnyx05), [Atrate](https://github.com/Atrate), [Tuchit](https://github.com/tuchit), [Linsui](https://github.com/linsui), [scrubjay55](https://github.com/scrubjay55), [Antyradek](https://github.com/Antyradek), [Ninja1998](https://github.com/NiNjA1998), [elea11](https://github.com/elea11).

## Author

Antonios Hazim
