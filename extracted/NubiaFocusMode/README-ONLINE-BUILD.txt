NUBIA FOCUS - ONLINE APK BUILD

1. Create or sign in to a GitHub account at https://github.com/
2. Create a NEW repository. You can name it NubiaFocusMode.
3. Upload the CONTENTS of this folder to the repository (not the outer folder).
   Make sure .github/workflows/build-apk.yml is included.
4. Open the repository's Actions tab.
5. Choose "Build Nubia Focus APK" and tap "Run workflow".
   If you uploaded to the main branch, the push may also start a build automatically.
6. Open the completed workflow run.
7. Under Artifacts, download "nubia-focus-debug-apk".
8. Extract the downloaded artifact ZIP. It contains app-debug.apk.
9. Transfer app-debug.apk to your Android phone and install it.

NOTE:
- This starter project currently provides a basic focus timer only.
- It does NOT yet reproduce OPPO Focus Mode or block other apps.
- A debug APK is suitable for testing. Keep the same signing key for future
  update builds if you later distribute the app.
