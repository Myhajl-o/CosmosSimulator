**Scope of work**

- Work exclusively on input and output functionality: terminal interaction, reading and writing data, and the corresponding Input/Output classes.
- The user develops all other parts of the application.
- You may read the entire project to understand its behavior and contracts, but may edit only files within the approved scope.
- Physics formulas, celestial object classification, domain models, the simulation engine, physics processors, calculators, and updaters are outside your implementation scope.
- If ownership of a class or method is unclear, ask the user before editing it.

**Approval before changes**

- Before editing, briefly explain the proposed changes, their purpose, and the files they will affect.
- Begin editing only after the user approves the proposal.
- Approval applies only to the described changes. Obtain additional approval before expanding the scope.
- If Input/Output requires changes elsewhere, describe the required contract or behavior to the user. Leave implementation of those changes to the user.
- Obtain separate approval before adding dependencies or modifying `pom.xml` or tool configuration.
- Questions, brainstorming, and requests for code review do not authorize file modifications.

**Working with existing code**

- Follow the agreed architecture and existing project conventions.
- Do not perform unrelated refactoring, renaming, or formatting.
- Do not overwrite or revert the user's changes.
- Report problems outside Input/Output without fixing them.
- Reuse existing contracts and functionality instead of duplicating them.

**Input behavior**

- Discard unfinished input when the user cancels.
- Add objects to the list only after input is complete and validation succeeds.
- Use the agreed validation and object creation mechanisms.
- Do not duplicate physics or object classification rules inside Input/Output code.

**Language requirements**

- Communicate with the user in Ukrainian.
- Write all new or modified code in English.
- Use English for class, interface, method, variable, constant, and package names.
- Write comments, Javadoc, test names, and developer documentation in English.
- Write application-generated text in English, including terminal menus, prompts, validation messages, error messages, and logs.
- Preserve user-provided text and external data in their original language.
- Do not rename existing identifiers or translate unrelated files solely to enforce these rules without approval.

**Completion report**

After completing approved work, report:
- Which files were changed.
- Which behavior was added or modified.
- Every new class and method, including its name and purpose.
- Any changes to existing method parameters or return values.
- Which checks were performed and their results.

Never claim that a check passed unless it was actually executed.

**Communication and decisions**

- Explain decisions concretely, referring to relevant classes and methods.
- Do not invent missing requirements.
- Ask the user when an unresolved requirement affects application behavior.
- Treat these rules as ongoing project constraints. Do not expand your implementation scope unless the user explicitly changes it.
