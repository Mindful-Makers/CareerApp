# User Stories

## Primary Source

**Our user stories are tracked in GitHub Issues and managed on our GitHub Project board.**

📋 **GitHub Project Board**: [Mindful Makers Kanban](https://github.com/orgs/Mindful-Makers/projects/5/views/1)  
🐛 **GitHub Issues**: [Job Fair Note App Issues](https://github.com/Mindful-Makers/CareerApp/issues)

This document serves as reference for user story format and best practices.

---

## User Story Format

We follow this standard format:
```
As a [user type],
I want [goal],
So that [benefit].
```

Each story includes **Acceptance Criteria** using rule-based or scenario-based format (stakeholder requirement).

### In GitHub Issues
- **Title**: Brief story title (e.g., "Quick Company Entry")
- **Description**: Full user story + acceptance criteria
- **Labels**: Priority (High/Medium/Low), Epic name, Status
- **Project**: Linked to Kanban board
- **Assignee**: Who's working on it

---

## Example Story

Below is an example of a user story showing our format. These are also created as GitHub issues for actual tracking.

---

### Story: Quick Company Entry
**Priority**: High  
**GitHub Issue**: [#XX]

As a student at a job fair,  
I want to quickly create a new company note,  
So that I can capture information while talking to recruiters.

**Acceptance Criteria:**
- User can select "New Company" option from menu
- User can enter company name (required)
- User can save note with minimal fields
- Note appears in company list
- Takes less than 30 seconds to create basic entry

---

## Creating User Stories in GitHub

### GitHub Issue Template

```markdown
**User Story**
As a [user type],
I want [goal],
So that [benefit].

**Acceptance Criteria**
- [ ] Criterion 1
- [ ] Criterion 2
- [ ] Criterion 3

**Definition of Done**
- [ ] Code written and tested
- [ ] Regression tests pass
- [ ] Code reviewed and approved
- [ ] Merged to main
- [ ] Acceptance criteria verified

**Related Issues**
- Depends on #XX
- Related to #YY
```

### Labels to Use
- **Priority**: `priority: high`, `priority: medium`, `priority: low`
- **Type**: `user-story`, `bug`, `task`
- **Status**: Managed via Project board columns

### Linking Issues
- Reference in commits: `Implements #15` or `Closes #15`
- Reference in PRs: `Closes #15`
- Link related issues in description

---

## Writing Good Acceptance Criteria

### Rule-Based
States requirements as rules:
- User **must** be able to...
- System **must** validate...
- Data **must** persist...

### Scenario-Based (Given-When-Then)
Describes specific scenarios:
- **Given** I'm viewing the company list
- **When** I select "New Company"
- **Then** I see the company entry prompts

Both formats meet stakeholder requirements. Use what works best for each story.

---

## Managing the Backlog

### In GitHub Project
1. **Backlog** column: All ideas and future work
2. **Ready** column: Refined stories ready to start
3. **In Progress**: Being actively developed
4. **Review**: In code review/testing
5. **Done**: Complete and merged

### Refinement Process
- Review backlog in weekly planning
- Break large stories into smaller ones
- Clarify acceptance criteria
- Move refined stories to "Ready"

---

## Future Development

**Current Scope**: Console application (Java + Ant)  
**Future Vision**: Mobile application

The console implementation focuses on core functionality and data management. Future iterations could adapt the same user stories for a mobile interface, leveraging the established data model and business logic.

---

**Primary Tracking**: GitHub Issues & Project Board  
**This Document**: Format reference and examples for course documentation  
**Last Updated**: [Date]
