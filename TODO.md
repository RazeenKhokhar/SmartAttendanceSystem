# Smart Attendance System - Fix Issues Plan

## Approved Plan Steps (from BLACKBOXAI)

**Status: In Progress**

### Phase 1: Clean Build System & Remove Duplicates
- [ ] Step 1.1: Create build.bat for clean compilation to classes/
- [ ] Step 1.2: Delete all stray .class files outside src/classes
- [ ] Step 1.3: Test build & run

### Phase 2: Code Fixes & Refactoring
- [ ] Step 2.1: Update src/util/FileHandler.java - Add saveStudents(List), full CSV rewrite, validation
- [ ] Step 2.2: Fix src/ui/StudentPanel.java - Use FileHandler for deleteSelected()
- [ ] Step 2.3: Update src/ui/AttendancePanel.java & ReportPanel.java - Minor polishes

### Phase 3: Performance Optimizations (TODO-Perf Phase 1)
- [ ] Step 3.1: Create src/util/DataCache.java - Shared student/attendance cache
- [ ] Step 3.2: Update src/ui/MainFrame.java - Load data once, pass to panels
- [ ] Step 3.3: Refactor panels to use cache, remove redundant FileHandler calls

### Phase 4: Documentation & Polish
- [ ] Step 4.1: Update README.md with build/run instructions
- [ ] Step 4.2: Clear/update TODO-Perf.md
- [ ] Step 4.3: Test full workflow (add/delete/mark/report/export)
- [ ] Step 4.4: attempt_completion

**Next: Phase 1**
