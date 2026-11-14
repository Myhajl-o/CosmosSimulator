
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
