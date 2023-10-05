Loop
{
    IfWinActive, ahk_class ProgramManager
    {
        IfWinExist, ahk_class TaskSwitcherWnd
        {
            ; Alt+Tab is pressed
            Break
        }
    }
    Sleep, 100
}
